package com.altaf.storyblog.ui.category

import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.ui.category.viewmodel.CategoryData
import com.altaf.storyblog.ui.category.viewmodel.CategoryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : BaseViewModel<CategoryData, CategoryEvent>()


