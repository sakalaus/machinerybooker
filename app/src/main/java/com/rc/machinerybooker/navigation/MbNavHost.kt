package com.rc.machinerybooker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rc.feature.machinery_order.navigation.machineryOrderRoute
import com.rc.machinerybooker.feature.machineryorderlist.navigation.machineryOrderListNavigationRoute
import com.rc.machinerybooker.feature.machineryorderlist.navigation.machineryOrderListRoute
import com.rc.machinerybooker.feature.settings.navigation.settingsRoute
import com.rc.machinerybooker.feature.welcome_screen.navigation.welcomeScreenNavigationRoute
import com.rc.machinerybooker.feature.welcome_screen.navigation.welcomeScreenRoute

@Composable
fun MbNavHost(
    navController: NavHostController,
    onWelcomeScreenShow: () -> Unit,
    welcomeScreenShown: Boolean,
    onBackClick: () -> Unit,
    innerPadding: PaddingValues,
    startDestination: String = welcomeScreenNavigationRoute
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues = innerPadding),
    ) {
        welcomeScreenRoute(
            onWelcomeScreenShow = onWelcomeScreenShow,
            onNavigateToMachineryOrderList = { navController.onNavigateToMachineryOrderList() }
        )
        machineryOrderRoute()
        machineryOrderListRoute(
            welcomeScreenShown = welcomeScreenShown,
            onNavigateToMachineryOrder = { navController.onNavigateToMachineryOrder() },
            onNavigateToWelcomeScreen = { navController.onNavigateToWelcomeScreen() }
        )
        settingsRoute()
    }
}

fun NavHostController.onNavigateToMachineryOrder(
) {
    navigate(machineryOrderRoute)
}


fun NavHostController.onNavigateToMachineryOrderList(
) {
    navigate(machineryOrderListNavigationRoute)
}

fun NavHostController.onNavigateToWelcomeScreen(
) {
    navigate(welcomeScreenNavigationRoute)
}
