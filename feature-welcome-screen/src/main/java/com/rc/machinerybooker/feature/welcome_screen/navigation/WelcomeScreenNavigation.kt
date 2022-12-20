package com.rc.machinerybooker.feature.welcome_screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rc.machinerybooker.feature.welcome_screen.screen.WelcomeScreenRoute

const val welcomeScreenNavigationRoute = "welcome_screen_route"

fun NavController.navigateToWelcomeScreen(navOptions: NavOptions? = null) {
    this.navigate(welcomeScreenNavigationRoute, navOptions)
}

fun NavGraphBuilder.welcomeScreenRoute(
    onScreenChanged: (String) -> Unit,
    onWelcomeScreenShow: () -> Unit,
    onNavigateToMachineryOrderList: () -> Unit
){
    composable(route = welcomeScreenNavigationRoute) {
        onScreenChanged(route ?: "")
        WelcomeScreenRoute(
            onWelcomeScreenShow = onWelcomeScreenShow,
            onNavigateToMachineryOrderList = onNavigateToMachineryOrderList
        )
    }
}

