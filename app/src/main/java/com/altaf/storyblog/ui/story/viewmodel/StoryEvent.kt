package com.altaf.storyblog.ui.story.viewmodel

import com.altaf.storyblog.domain.model.Story

/**
 * Sealed class representing one-time events for the Story screen
 */
sealed class StoryEvent {
    data class NavigateToSingleStory(val story: Story) : StoryEvent()
    object Empty : StoryEvent()
}
