package com.altaf.storyblog.ui.home.viewmodel

import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.Story

// Sealed class for one-time events in Home screen
sealed class HomeEvent {
    object Empty : HomeEvent()
    data class NavigateToCategoryWiseStory(val category: Category) : HomeEvent()
    object NavigateToCategory : HomeEvent()
    data class NavigateToSingleStory(val story: Story): HomeEvent()
    object NavigateToStory: HomeEvent()
}
