package com.altaf.storyblog.ui.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.altaf.storyblog.common.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainData, MainEvent>() {

    val categoryName = MutableLiveData<String>("")
}
