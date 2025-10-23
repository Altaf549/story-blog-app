package com.altaf.storyblog.ui.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.recyclerview.widget.LinearLayoutManager
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentStoryBinding
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.ui.adapter.StoryLoadStateAdapter
import com.altaf.storyblog.ui.adapter.StoryPagingAdapter
import com.altaf.storyblog.ui.story.viewmodel.StoryEvent
import com.altaf.storyblog.ui.story.viewmodel.StoryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryFragment : BaseFragment<StoryViewModel, FragmentStoryBinding>() {

    private lateinit var storyAdapter: StoryPagingAdapter

    override fun getViewModelClass(): Class<StoryViewModel> = StoryViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentStoryBinding = FragmentStoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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


