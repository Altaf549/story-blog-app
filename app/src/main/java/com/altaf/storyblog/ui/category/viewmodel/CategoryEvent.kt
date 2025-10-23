package com.altaf.storyblog.ui.category.viewmodel

import com.altaf.storyblog.domain.model.Category

/**
 * Sealed class for one-time events in Category screen
 */
sealed class CategoryEvent {
    object Empty : CategoryEvent()
    object NavigateToCategoryWiseStory : CategoryEvent()
}
