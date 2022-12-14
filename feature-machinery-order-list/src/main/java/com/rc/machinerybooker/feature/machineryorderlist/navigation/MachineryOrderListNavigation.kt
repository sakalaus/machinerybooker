package com.rc.machinerybooker.feature.machineryorderlist.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rc.machinerybooker.feature.machineryorderlist.screen.OrderListRoute

const val machineryOrderListNavigationRoute = "machinery_order_list_route"

fun NavController.navigateToMachineryOrderList(navOptions: NavOptions? = null) {
    this.navigate(machineryOrderListNavigationRoute, navOptions)
}

fun NavGraphBuilder.machineryOrderListRoute(
    welcomeScreenShown: Boolean,
    onNavigateToMachineryOrder: () -> Unit,
    onNavigateToWelcomeScreen: () -> Unit
) {
    composable(route = machineryOrderListNavigationRoute) {
        OrderListRoute(
            welcomeScreenShown = welcomeScreenShown,
            onNavigateToMachineryOrder = onNavigateToMachineryOrder,
            onNavigateToWelcomeScreen = onNavigateToWelcomeScreen
        )
    }
}

