package com.rc.machinerybooker.feature.settings.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rc.machinerybooker.feature.settings.screen.SettingsRoute

const val settingsNavigationRoute = "settings_route"

fun NavController.navigateToSettings(navOptions: NavOptions? = null) {
    this.navigate(settingsNavigationRoute, navOptions)
}

fun NavGraphBuilder.settingsRoute() {
    composable(route = settingsNavigationRoute) {
        SettingsRoute()
    }
}