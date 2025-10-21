package com.altaf.storyblog.ui.main.viewmodel

sealed class MainEvent {
    object ShowToast : MainEvent()
    object NavigateToNextScreen : MainEvent()
}