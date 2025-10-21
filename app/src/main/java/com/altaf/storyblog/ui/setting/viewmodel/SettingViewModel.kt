package com.altaf.storyblog.ui.setting

import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.ui.setting.viewmodel.SettingData
import com.altaf.storyblog.ui.setting.viewmodel.SettingEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingViewModel @Inject constructor() : BaseViewModel<SettingData, SettingEvent>()

