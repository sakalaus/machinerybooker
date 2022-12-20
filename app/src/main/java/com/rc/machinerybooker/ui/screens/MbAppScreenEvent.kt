package com.rc.machinerybooker.ui.screens

sealed interface MbAppScreenEvent{
    class ScreenChanged(val destination: String): MbAppScreenEvent
    object WelcomeScreenShown: MbAppScreenEvent
}