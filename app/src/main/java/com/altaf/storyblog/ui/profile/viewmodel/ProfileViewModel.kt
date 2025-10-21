package com.altaf.storyblog.ui.profile

import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.ui.profile.viewmodel.ProfileData
import com.altaf.storyblog.ui.profile.viewmodel.ProfileEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor() : BaseViewModel<ProfileData, ProfileEvent>()


