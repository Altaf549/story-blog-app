package com.altaf.storyblog.ui.home.viewmodel

import androidx.lifecycle.viewModelScope
import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.domain.model.HomeData
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.usecase.home.GetHomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getHomeDataUseCase: GetHomeDataUseCase
) : BaseViewModel<HomeState, HomeEvent>() {

    private val _uiState = MutableStateFlow<HomeState>(HomeState.Loading)
    val uiState: StateFlow<HomeState> = _uiState

    private val _uiEvent = MutableStateFlow<HomeEvent>(HomeEvent.Empty)
    val uiEvent: StateFlow<HomeEvent> = _uiEvent

    init {
        loadHomeData()
    }

    fun loadHomeData() {
        viewModelScope.launch {
            _uiState.value = HomeState.Loading
            when (val result = getHomeDataUseCase()) {
                is NetworkResult.Success -> {
                    val homeData = result.data
                    _uiState.value = if (homeData?.stories?.isEmpty() == true && homeData.banners.isEmpty()) {
                        HomeState.Empty
                    } else {
                        HomeState.Success(homeData ?: HomeData())
                    }
                }
                is NetworkResult.Error -> {
                    _uiState.value = HomeState.Error(result.message ?: "An unknown error occurred")
                }
            }
        }
    }

    fun onRetry() {
        loadHomeData()
    }
    
    fun onSeeAllCategoriesClicked() {
        _uiEvent.value = HomeEvent.NavigateToCategory
    }

    fun onCategoriesClicked() {
        _uiEvent.value = HomeEvent.NavigateToCategoryWiseStory
    }

    fun clearEvent() {
        _uiEvent.value = HomeEvent.Empty
    }
}
