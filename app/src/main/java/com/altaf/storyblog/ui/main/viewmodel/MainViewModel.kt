package com.altaf.storyblog.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import com.altaf.storyblog.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel

class MainViewModel : BaseViewModel<MainData, MainEvent>()
