package com.rc.machinerybooker.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.rc.machinerybooker.feature.machineryorderlist.navigation.machineryOrderListNavigationRoute
import com.rc.machinerybooker.feature.machineryorderlist.navigation.machineryOrderListRoute
import com.rc.machinerybooker.feature.settings.navigation.settingsRoute

@Composable
fun MbNavHost (
    navController: NavHostController,
    onBackClick: () -> Unit,
    innerPadding: PaddingValues,
    startDestination: String = machineryOrderListNavigationRoute){
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = Modifier.padding(paddingValues = innerPadding),
    ) {
        machineryOrderListRoute()
        settingsRoute()
    }
}
