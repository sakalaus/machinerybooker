package com.rc.feature.machinery_order.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.rc.feature.machinery_order.screen.MachineryOrderRoute

const val machineryOrderRoure = "machinery_order_route"

fun NavController.navigateToMachineryOrderList(navOptions: NavOptions? = null) {
    this.navigate(machineryOrderRoure, navOptions)
}

fun NavGraphBuilder.machineryOrderListRoute() {
    composable(route = machineryOrderRoure) {
        MachineryOrderRoute()
    }
}