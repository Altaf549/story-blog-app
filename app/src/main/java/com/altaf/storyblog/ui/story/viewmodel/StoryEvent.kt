package com.altaf.storyblog.ui.story.viewmodel

/**
 * Sealed class representing one-time events for the Story screen
 */
sealed class StoryEvent {
    object NavigateToSingleStory : StoryEvent()
    object Empty : StoryEvent()
}
