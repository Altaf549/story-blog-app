package com.altaf.storyblog.ui.home.viewmodel

import com.altaf.storyblog.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor() : BaseViewModel<HomeData, HomeEvent>()


