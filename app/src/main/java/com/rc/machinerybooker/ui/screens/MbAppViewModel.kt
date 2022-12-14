package com.rc.machinerybooker.ui.screens

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MbAppViewModel @Inject constructor(
) : ViewModel() {

    private val _uiState = MutableStateFlow(MbAppDataState())
    val uiState = _uiState.asStateFlow()

    fun onEvent(event: MbAppScreenEvent) {
        when (event) {
            MbAppScreenEvent.WelcomeScreenShown -> _uiState.update { it.copy(welcomeScreenShown = true) }
        }
    }


}