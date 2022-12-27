package com.rc.machinerybooker.ui.screens

import com.rc.machinerybooker.feature.machineryorderlist.navigation.machineryOrderListNavigationRoute

data class MbAppDataState(
    val welcomeScreenShown: Boolean = true,
    val currentDestination: String = ""
){

    val shouldShowSystemBarsAndButtons: Boolean
    get() = welcomeScreenShown

    val shouldShowFab: Boolean
    get() = (currentDestination == machineryOrderListNavigationRoute)

}