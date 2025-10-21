package com.altaf.storyblog.ui.category.viewmodel

import com.altaf.storyblog.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor() : BaseViewModel<CategoryData, CategoryEvent>()


