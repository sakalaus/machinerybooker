package com.rc.feature.machinery_order.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rc.feature.machinery_order.screen.MachineryOrderRoute

const val machineryOrderRoute = "machinery_order_route"

fun NavController.navigateToMachineryOrder(navOptions: NavOptions? = null) {
    this.navigate(machineryOrderRoute, navOptions)
}

fun NavGraphBuilder.machineryOrderRoute(
    onScreenChanged: (String) -> Unit
) {
    onScreenChanged(route ?: "")
    composable(route = machineryOrderRoute) {
        MachineryOrderRoute()
    }
}
