package com.altaf.storyblog.ui.story.viewmodel

import com.altaf.storyblog.domain.model.Story

/**
 * UI State for Story screen
 */
sealed class StoryState {
    object Loading : StoryState()
    data class Success(val stories: MutableList<Story>) : StoryState()
    data class Error(val message: String) : StoryState()
    object Empty : StoryState()
}
