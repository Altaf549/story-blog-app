package com.altaf.storyblog.ui.home.viewmodel

import com.altaf.storyblog.domain.model.HomeData

/**
 * UI State for Home screen
 */
sealed class HomeState {
    object Loading : HomeState()
    data class Success(val homeData: HomeData) : HomeState()
    data class Error(val message: String) : HomeState()
    object Empty : HomeState()
}