package com.rc.machinerybooker.ui.screens

import androidx.lifecycle.ViewModel
import com.rc.machinerybooker.ui.screens.MbAppScreenEvent.*
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
            WelcomeScreenShown -> markWelcomeScreenAsShown()
            is ScreenChanged -> onScreenChanged(event.destination)
        }
    }

    private fun onScreenChanged(destination: String) {
        when (destination) {

            else -> Unit
        }
    }

    private fun markWelcomeScreenAsShown() = _uiState.update {
        it.copy(welcomeScreenShown = true)
    }

}