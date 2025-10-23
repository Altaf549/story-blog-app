package com.altaf.storyblog.ui.category.viewmodel

import com.altaf.storyblog.domain.model.Category

/**
 * UI State for Category screen
 */
sealed class CategoryState {
    object Loading : CategoryState()
    data class Success(val categories: MutableList<Category>) : CategoryState()
    data class Error(val message: String) : CategoryState()
    object Empty : CategoryState()
}
