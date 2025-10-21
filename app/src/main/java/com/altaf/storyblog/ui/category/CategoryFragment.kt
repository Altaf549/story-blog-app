package com.altaf.storyblog.ui.category

import android.view.LayoutInflater
import android.view.ViewGroup
import com.altaf.storyblog.common.base.BaseFragment
import com.altaf.storyblog.databinding.FragmentCategoryBinding
import com.altaf.storyblog.ui.category.viewmodel.CategoryViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryFragment : BaseFragment<CategoryViewModel, FragmentCategoryBinding>() {
    override fun getViewModelClass(): Class<CategoryViewModel> = CategoryViewModel::class.java
    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentCategoryBinding = FragmentCategoryBinding.inflate(inflater, container, false)
}


