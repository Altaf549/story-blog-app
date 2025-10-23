package com.altaf.storyblog.ui.home.viewmodel

// Sealed class for one-time events in Home screen
sealed class HomeEvent {
    object Empty : HomeEvent()
    object NavigateToCategoryWiseStory : HomeEvent()
    object NavigateToCategory : HomeEvent()
}
