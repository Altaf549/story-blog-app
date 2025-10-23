package com.altaf.storyblog.ui.category.viewmodel

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.usecase.category.GetCategoriesUseCase
import com.altaf.storyblog.ui.home.viewmodel.HomeEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val getCategoriesUseCase: GetCategoriesUseCase
) : BaseViewModel<CategoryState, CategoryEvent>() {

    private val _uiState = MutableStateFlow<CategoryState>(CategoryState.Loading)
    val uiState: StateFlow<CategoryState> = _uiState.asStateFlow()

    private val _uiEvent = MutableStateFlow<CategoryEvent>(CategoryEvent.Empty)
    val uiEvent: StateFlow<CategoryEvent> = _uiEvent

    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            _uiState.update { CategoryState.Loading }
            
            when (val result = getCategoriesUseCase()) {
                is NetworkResult.Success -> {
                    val categories = result.data ?: emptyList()
                    _uiState.update { 
                        if (categories.isEmpty()) CategoryState.Empty 
                        else CategoryState.Success(categories as MutableList<Category>)
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.update { CategoryState.Error(result.message ?: "Unknown error") }
                }
            }
        }
    }

    fun onCategoriesClicked(category: Category) {
        _uiEvent.value = CategoryEvent.NavigateToCategoryWiseStory(category)
    }

    fun clearEvent() {
        _uiEvent.value = CategoryEvent.Empty
    }
}
