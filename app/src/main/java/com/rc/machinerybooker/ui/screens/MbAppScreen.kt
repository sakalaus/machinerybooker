package com.rc.machinerybooker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rc.machinerybooker.navigation.MbNavHost
import com.rc.machinerybooker.navigation.MbTopLevelDestination

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MbAppScreen(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    appState: MbAppState = rememberMbAppState(
        windowSizeClass = windowSizeClass
    ),
) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme()

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
    }

    Scaffold(
        topBar = {
            MbTopBar(
                actionIcon = Icons.Default.MoreVert,
                actionIconContentDescription = "Action icon",
                onActionClick = {}
            )
        },
        bottomBar = {
            MbBottomBar(
                destinations = appState.topLevelDestinations,
                onNavigateToDestination = appState::navigateToTopLevelDestination,
                currentDestination = appState.currentDestination
            )
        }
    ) { innerPadding ->
        MbNavHost(
            navController = appState.navController,
            onBackClick = appState::onBackClick,
            innerPadding = innerPadding
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MbTopBar(
    modifier: Modifier = Modifier,
    actionIcon: ImageVector,
    actionIconContentDescription: String,
    onActionClick: () -> Unit,
    colors: TopAppBarColors = TopAppBarDefaults.centerAlignedTopAppBarColors()
){
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {Text("Pregol machinery")},
        navigationIcon = {},
        colors = colors,
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = actionIcon,
                    contentDescription = actionIconContentDescription,
                    tint = MaterialTheme.colorScheme.onSurface
                )
            }
        },
    )
}

@Composable
fun RowScope.MbBottomBarItem(
    modifier: Modifier = Modifier,
    selected: Boolean,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    selectedIcon: @Composable () -> Unit = icon,
    enabled: Boolean = true,
    label: @Composable (() -> Unit)? = null,
    alwaysShowLabel: Boolean = true
){
    NavigationBarItem(
        modifier = modifier,
        label = label ?: {},
        selected =selected,
        icon = if (selected) selectedIcon else icon,
        onClick = onClick,
        enabled = enabled,
        alwaysShowLabel = alwaysShowLabel,
        colors = NavigationBarItemDefaults.colors(
            selectedIconColor = MbNavigationDefaults.navigationSelectedItemColor(),
            unselectedIconColor = MbNavigationDefaults.navigationContentColor(),
            selectedTextColor = MbNavigationDefaults.navigationSelectedItemColor(),
            unselectedTextColor = MbNavigationDefaults.navigationContentColor(),
            indicatorColor = MbNavigationDefaults.navigationIndicatorColor()
        )
    )
}

@Composable
fun MbBottomBar(
    destinations: List<MbTopLevelDestination>,
    onNavigateToDestination: (MbTopLevelDestination) -> Unit,
    currentDestination: NavDestination?) {

    NavigationBar(
        tonalElevation = 0.dp,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            MbBottomBarItem(
                selected = selected,
                onClick = {onNavigateToDestination(destination)},
                icon = {
                    val icon = if (selected) {
                        destination.selectedIcon
                    } else {
                        destination.unselectedIcon
                    }
                    Icon(
                        imageVector = icon,
                        contentDescription = null
                    )
                },
                label = { Text(stringResource(destination.iconTextId)) }
            )
        }
    }

}

object MbNavigationDefaults {
    @Composable
    fun navigationContentColor() = MaterialTheme.colorScheme.onSurfaceVariant
    @Composable
    fun navigationSelectedItemColor() = MaterialTheme.colorScheme.onPrimaryContainer
    @Composable
    fun navigationIndicatorColor() = MaterialTheme.colorScheme.primaryContainer
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(destination: MbTopLevelDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false

