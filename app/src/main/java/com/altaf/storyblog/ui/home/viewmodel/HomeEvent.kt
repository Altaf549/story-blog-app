package com.altaf.storyblog.ui.home.viewmodel

import com.altaf.storyblog.domain.model.Category

// Sealed class for one-time events in Home screen
sealed class HomeEvent {
    object Empty : HomeEvent()
    data class NavigateToCategoryWiseStory(val category: Category) : HomeEvent()
    object NavigateToCategory : HomeEvent()
    object NavigateToSingleStory: HomeEvent()
    object NavigateToStory: HomeEvent()
}
