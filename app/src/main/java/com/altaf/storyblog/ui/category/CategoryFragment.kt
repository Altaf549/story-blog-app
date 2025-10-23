package com.altaf.storyblog.ui.category

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.altaf.storyblog.R
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentCategoryBinding
import com.altaf.storyblog.ui.adapter.CategoryAdapter
import com.altaf.storyblog.ui.category.viewmodel.CategoryEvent
import com.altaf.storyblog.ui.category.viewmodel.CategoryState
import com.altaf.storyblog.ui.category.viewmodel.CategoryViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {

    private lateinit var categoryAdapter: CategoryAdapter

    override fun getViewModelClass(): Class<CategoryViewModel> = CategoryViewModel::class.java

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCategories()
        observeViewModel()
        observeEvents()
    }

    private fun setupCategories() {
        categoryAdapter = CategoryAdapter()
        binding.rvCategories.apply {
            adapter = categoryAdapter
            setHasFixedSize(true)
        }

        // Handle category item click
        categoryAdapter.onItemClick = { category ->
            viewModel.onCategoriesClicked()
        }
    }

    private fun observeEvents() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiEvent.collect { event ->
                when (event) {
                    is CategoryEvent.NavigateToCategoryWiseStory -> navigateToCategoryWiseStory()
                    else -> {}
                }
            }
        }
    }

    private fun navigateToCategoryWiseStory() {
        // Navigate to category wise story with proper back stack handling
        findNavController().navigate(
            R.id.categoryWiseStoryFragment,
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

    private fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is CategoryState.Loading -> showLoading(true)
                    is CategoryState.Success -> {
                        showLoading(false)
                        categoryAdapter.submitList(state.categories)
                        showEmptyState(state.categories.isEmpty())
                    }
                    is CategoryState.Error -> {
                        showLoading(false)
                        showMessage(state.message)
                    }
                    is CategoryState.Empty -> {
                        showLoading(false)
                        showEmptyState(true)
                    }
                }
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.apply {
            progressBar.isVisible = isLoading
            rvCategories.isVisible = !isLoading
            tvEmpty.isVisible = false
        }
    }

    private fun showEmptyState(isEmpty: Boolean) {
        binding.apply {
            tvEmpty.isVisible = isEmpty
            rvCategories.isVisible = !isEmpty
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.clearEvent()
    }

    private fun showMessage(message: String) {
        view?.let {
            Snackbar.make(it, message, Snackbar.LENGTH_SHORT).show()
        }
    }
}
