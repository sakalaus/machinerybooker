package com.rc.machinerybooker.ui.screens

data class MbAppDataState(
    val welcomeScreenShown: Boolean = false
){
    val shouldShowSystemBarsAndButtons: Boolean
    get() = welcomeScreenShown
}