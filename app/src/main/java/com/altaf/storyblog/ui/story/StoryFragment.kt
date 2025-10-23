package com.altaf.storyblog.ui.story

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentStoryBinding
import com.altaf.storyblog.ui.adapter.StoryAdapter
import com.altaf.storyblog.ui.category.viewmodel.CategoryEvent
import com.altaf.storyblog.ui.story.viewmodel.StoryEvent
import com.altaf.storyblog.ui.story.viewmodel.StoryState
import com.altaf.storyblog.ui.story.viewmodel.StoryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoryFragment : BaseFragment<StoryViewModel, FragmentStoryBinding>() {

    private lateinit var storyAdapter: StoryAdapter

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
        storyAdapter = StoryAdapter()

        // Initialize RecyclerView
        binding.rvStories.apply {
            adapter = storyAdapter
            setHasFixedSize(true)
        }
        // Handle story item clicks
        storyAdapter.onItemClick = { story ->
            viewModel.onStoryClicked()
        }

        storyAdapter.onReadMoreClick = { story ->
            viewModel.onStoryClicked()
        }
    }

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is StoryState.Loading -> {
                        binding.tvEmptyState.isVisible = false
                    }
                    is StoryState.Success -> {
                        binding.progressBar.isVisible = false
                        binding.tvEmptyState.isVisible = state.stories.isEmpty()
                        storyAdapter.submitList(state.stories)
                    }
                    is StoryState.Error -> {
                        binding.progressBar.isVisible = false
                        showErrorMessage(state.message)
                    }
                    is StoryState.Empty -> {
                        binding.progressBar.isVisible = false
                        binding.tvEmptyState.isVisible = true
                    }
                }
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


