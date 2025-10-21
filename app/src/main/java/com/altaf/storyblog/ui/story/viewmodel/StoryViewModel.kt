package com.altaf.storyblog.ui.story.viewmodel

import com.altaf.storyblog.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StoryViewModel @Inject constructor() : BaseViewModel<StoryData, StoryEvent>()


