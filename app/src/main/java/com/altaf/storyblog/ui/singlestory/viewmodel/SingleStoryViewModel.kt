package com.altaf.storyblog.ui.singlestory.viewmodel

import com.altaf.storyblog.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SingleStoryViewModel @Inject constructor() : BaseViewModel<SingleStoryState, SingleStoryEvent>()