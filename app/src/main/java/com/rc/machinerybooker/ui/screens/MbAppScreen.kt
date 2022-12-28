package com.rc.machinerybooker.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.rc.machinerybooker.navigation.MbNavHost
import com.rc.machinerybooker.navigation.MbTopLevelDestination
import com.rc.machinerybooker.R
import com.rc.machinerybooker.navigation.onNavigateToMachineryOrder
import com.rc.machinerybooker.ui.screens.MbAppScreenEvent.*

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MbAppScreen(
    windowSizeClass: androidx.compose.material3.windowsizeclass.WindowSizeClass,
    appState: MbAppState = rememberMbAppState(
        windowSizeClass = windowSizeClass
    ),
    appViewModel: MbAppViewModel = hiltViewModel()
) {
    val systemUiController = rememberSystemUiController()
    val darkIcons = isSystemInDarkTheme()
    val currentAppDataState = appViewModel.uiState.collectAsState().value

    SideEffect {
        systemUiController.setSystemBarsColor(Color.Transparent, darkIcons = darkIcons)
    }

    Scaffold(
        topBar = {
            if (currentAppDataState.shouldShowSystemBarsAndButtons) {
                MbTopBar(
                    actionIcon = Icons.Default.MoreVert,
                    actionIconContentDescription = "Action icon",
                    onActionClick = {}
                )
            }
        },
        bottomBar = {
            if (currentAppDataState.shouldShowSystemBarsAndButtons) {
                MbBottomBar(
                    destinations = appState.topLevelDestinations,
                    onNavigateToDestination = appState::navigateToTopLevelDestination,
                    currentDestination = appState.currentDestination
                )
            }
        },
        floatingActionButton = {
            if (currentAppDataState.shouldShowSystemBarsAndButtons && currentAppDataState.shouldShowFab) {
                ExtendedFloatingActionButton(
                    modifier = Modifier,
                    containerColor = FloatingActionButtonDefaults.containerColor.copy(alpha = 0.8f),
                    onClick = { appState.navController.onNavigateToMachineryOrder(-1) },
                ) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = stringResource(id = R.string.add_new_feminine),
                    )
                    Text(text = stringResource(id = R.string.add_new_feminine))
                }
            }
        }
    ) { innerPadding ->
        MbNavHost(
            navController = appState.navController,
            welcomeScreenShown = currentAppDataState.welcomeScreenShown,
            onWelcomeScreenShow = { appViewModel.onEvent(WelcomeScreenShown) },
            onBackClick = appState::onBackClick,
            onScreenChanged = { destination -> appViewModel.onEvent(ScreenChanged(destination)) },
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
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        title = {
            Text(
                style = MaterialTheme.typography.titleMedium,
                text = stringResource(id = R.string.machinery_orders)
            )
        },
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
) {
    NavigationBarItem(
        modifier = modifier,
        label = label ?: {},
        selected = selected,
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
    currentDestination: NavDestination?
) {

    NavigationBar(
        tonalElevation = 20.dp,
    ) {
        destinations.forEach { destination ->
            val selected = currentDestination.isTopLevelDestinationInHierarchy(destination)
            MbBottomBarItem(
                selected = selected,
                onClick = { onNavigateToDestination(destination) },
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
                label = {
                    Text(
                        color = Color.Black,
                        text = stringResource(destination.iconTextId)
                    )
                }
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

