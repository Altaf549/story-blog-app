package com.altaf.storyblog.ui.story

import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.ui.story.viewmodel.StoryData
import com.altaf.storyblog.ui.story.viewmodel.StoryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor() : BaseViewModel<StoryData, StoryEvent>()


