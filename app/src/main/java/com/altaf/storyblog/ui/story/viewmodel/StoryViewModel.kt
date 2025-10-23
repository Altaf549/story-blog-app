package com.altaf.storyblog.ui.story.viewmodel

import androidx.lifecycle.viewModelScope
import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.usecase.story.GetStoriesUseCase
import com.altaf.storyblog.ui.category.viewmodel.CategoryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor(private val getStoriesUseCase: GetStoriesUseCase) : BaseViewModel<StoryState, StoryEvent>() {

    private val _uiState = MutableStateFlow<StoryState>(StoryState.Loading)
    val uiState: StateFlow<StoryState> = _uiState.asStateFlow()

    private val _uiEvent = MutableStateFlow<StoryEvent>(StoryEvent.Empty)
    val uiEvent: StateFlow<StoryEvent> = _uiEvent.asStateFlow()

    private var currentPage = 1
    private val pageSize = 15
    private var isLastPage = false
    private var isLoading = false

    init {
        loadStories()
    }

    fun loadStories() {
        if (isLoading || isLastPage) return
        
        viewModelScope.launch {
            isLoading = true
            _uiState.update { StoryState.Loading }
            
            when (val result = getStoriesUseCase(currentPage, pageSize)) {
                is NetworkResult.Success -> {
                    val stories = result.data ?: emptyList()
                    _uiState.update {
                        if (stories.isEmpty()) {
                            isLastPage = true
                            StoryState.Empty
                        } else {
                            currentPage++
                            StoryState.Success(stories.toMutableList())
                        }
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.update { StoryState.Error(result.message ?: "Unknown error") }
                }
            }
            
            isLoading = false
        }
    }
    
    fun refresh() {
        currentPage = 1
        isLastPage = false
        loadStories()
    }

    fun onStoryClicked() {
        _uiEvent.value = StoryEvent.NavigateToSingleStory
    }

    fun clearEvent() {
        _uiEvent.value = StoryEvent.Empty
    }
}


