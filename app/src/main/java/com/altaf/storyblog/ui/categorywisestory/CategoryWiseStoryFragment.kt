package com.altaf.storyblog.ui.categorywisestory

import android.os.Bundle
import android.view.View
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentCategoryWiseStoryBinding
import com.altaf.storyblog.ui.categorywisestory.viewmodel.CategoryWiseStoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryWiseStoryFragment : BaseFragment<CategoryWiseStoryViewModel, FragmentCategoryWiseStoryBinding>() {

    override fun getViewModelClass(): Class<CategoryWiseStoryViewModel> = CategoryWiseStoryViewModel::class.java

    override fun getViewBinding(
        inflater: android.view.LayoutInflater,
        container: android.view.ViewGroup?
    ): FragmentCategoryWiseStoryBinding = FragmentCategoryWiseStoryBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        observeViewModel()
    }

    private fun setupToolbar() {

    }

    private fun setupRecyclerView() {
        // TODO: Setup RecyclerView with adapter
    }

    private fun observeViewModel() {
        // TODO: Observe ViewModel state
    }
}
