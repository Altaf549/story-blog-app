package com.altaf.storyblog.ui.categorywisestory

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentCategoryWiseStoryBinding
import com.altaf.storyblog.ui.adapter.StoryLoadStateAdapter
import com.altaf.storyblog.ui.adapter.StoryPagingAdapter
import com.altaf.storyblog.ui.categorywisestory.viewmodel.CategoryWiseStoryViewModel
import com.altaf.storyblog.ui.story.viewmodel.StoryEvent
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryWiseStoryFragment : BaseFragment<CategoryWiseStoryViewModel, FragmentCategoryWiseStoryBinding>() {
    private lateinit var storyAdapter: StoryPagingAdapter
    private var categoryId: Long = -1
    private var categoryName: String = ""
    private var categorySlug: String = ""

    override fun getViewModelClass(): Class<CategoryWiseStoryViewModel> = CategoryWiseStoryViewModel::class.java

    override fun getViewBinding(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?
    ): FragmentCategoryWiseStoryBinding = FragmentCategoryWiseStoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Get category data from arguments
        arguments?.let { args ->
            categoryId = args.getLong("category_id", -1)
            categoryName = args.getString("category_name", "")
            categorySlug = args.getString("category_slug", "")
            viewModel.setStoriesByCategory(categorySlug)
        }
        setupStories()
        observeViewModel()
        observeEvents()
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is StoryEvent.NavigateToSingleStory -> navigateToSingleStory()
                    else -> {}
                }
            }
        }
    }

    private fun navigateToSingleStory() {
        // Navigate to category wise story with proper back stack handling
        findNavController().navigate(
            R.id.singleStoryFragment,
            null,
            NavOptions.Builder()
                .setLaunchSingleTop(true)
                .setEnterAnim(androidx.navigation.ui.R.anim.nav_default_enter_anim)
                .setExitAnim(androidx.navigation.ui.R.anim.nav_default_exit_anim)
                .setPopEnterAnim(androidx.navigation.ui.R.anim.nav_default_pop_enter_anim)
                .setPopExitAnim(androidx.navigation.ui.R.anim.nav_default_pop_exit_anim)
                .build()
        )
    }

    private fun setupStories() {
        storyAdapter = StoryPagingAdapter()

        // Initialize RecyclerView
        binding.rvStories.apply {
            adapter = storyAdapter.withLoadStateFooter(
                footer = StoryLoadStateAdapter { storyAdapter.retry() }
            )
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }

        // Handle story item clicks
        storyAdapter.onItemClick = { story ->
            viewModel.onStoryClicked()
        }

        storyAdapter.onReadMoreClick = { story ->
            viewModel.onStoryClicked()
        }

        // Observe load state for showing loading and error states
        storyAdapter.addLoadStateListener { loadState ->
            binding.apply {
                // Show loading indicator for initial load
                progressBar.isVisible = loadState.refresh is LoadState.Loading


                // Show empty state if there are no items and no error
                val isEmpty = loadState.refresh is LoadState.NotLoading && storyAdapter.itemCount == 0
                tvEmptyState.isVisible = isEmpty

                if (loadState.refresh is LoadState.NotLoading &&
                    loadState.append.endOfPaginationReached &&
                    storyAdapter.itemCount == 0) {
                    tvEmptyState.visibility = View.VISIBLE
                } else {
                    tvEmptyState.visibility = View.GONE
                }
            }
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            // Collect the paging data
            viewModel.storiesFlow.collectLatest { pagingData ->
                storyAdapter.submitData(pagingData)
            }
        }
    }

    private fun showErrorMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_LONG).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearEvent()
    }
}
