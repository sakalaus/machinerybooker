package com.rc.machinerybooker.ui.screens

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.rc.machinerybooker.feature.machineryorderlist.navigation.machineryOrderListNavigationRoute
import com.rc.machinerybooker.feature.machineryorderlist.navigation.navigateToMachineryOrderList
import com.rc.machinerybooker.feature.settings.navigation.navigateToSettings
import com.rc.machinerybooker.feature.settings.navigation.settingsNavigationRoute
import com.rc.machinerybooker.navigation.MbTopLevelDestination
import com.rc.machinerybooker.navigation.MbTopLevelDestination.*
import kotlinx.coroutines.CoroutineScope

@Composable
fun rememberMbAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    windowSizeClass: WindowSizeClass
): MbAppState =
    MbAppState(
        navController = navController,
        coroutineScope = coroutineScope,
        windowSizeClass = windowSizeClass,
    )

class MbAppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
    val windowSizeClass: WindowSizeClass,
) {

    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: MbTopLevelDestination?
        @Composable get() = when (currentDestination?.route) {
            machineryOrderListNavigationRoute -> MACHINERY_ORDER_LIST
            settingsNavigationRoute -> SETTINGS
            else -> null
        }

    val topLevelDestinations = values().asList()

    fun navigateToTopLevelDestination(topLevelDestination: MbTopLevelDestination) {

        val topLevelNavOptions = navOptions {

            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }

        when (topLevelDestination) {
            MACHINERY_ORDER_LIST -> navController.navigateToMachineryOrderList(topLevelNavOptions)
            SETTINGS -> navController.navigateToSettings(topLevelNavOptions)
        }
    }

    fun onBackClick() {
        navController.popBackStack()
    }

}