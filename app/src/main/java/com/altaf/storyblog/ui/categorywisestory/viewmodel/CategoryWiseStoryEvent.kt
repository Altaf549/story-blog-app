package com.altaf.storyblog.ui.categorywisestory.viewmodel

import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.ui.story.viewmodel.StoryEvent

sealed class CategoryWiseStoryEvent {
    data class NavigateToSingleStory(val story: Story) : CategoryWiseStoryEvent()
    object Empty : CategoryWiseStoryEvent()
}