package com.rc.machinerybooker.ui.screens

data class MbAppDataState(
    val welcomeScreenShown: Boolean = true // TODO Change to false if welcome screen is welcome
){
    val shouldShowSystemBarsAndButtons: Boolean
    get() = welcomeScreenShown
}