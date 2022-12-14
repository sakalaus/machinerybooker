package com.rc.machinerybooker.feature.welcome_screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import com.rc.machinerybooker.feature.welcome_screen.R as welcomeScreenRes

@Composable
fun WelcomeScreenRoute(
    onNavigateToMachineryOrderList: () -> Unit,
    onWelcomeScreenShow: () -> Unit,
) {
    WelcomeScreenScreen(
        onWelcomeScreenShow = onWelcomeScreenShow,
        onNavigateToMachineryOrderList = onNavigateToMachineryOrderList
    )
}

@Composable
fun WelcomeScreenScreen(
    onWelcomeScreenShow: () -> Unit,
    onNavigateToMachineryOrderList: () -> Unit
) {
    LaunchedEffect(key1 = true) {
        delay(2000L)
        onWelcomeScreenShow()
        onNavigateToMachineryOrderList()
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 36.dp),
            painter = painterResource(welcomeScreenRes.drawable.logo),
            contentDescription = ""
        )
    }
}

@Composable
@Preview
fun WelcomeScreenPreview() {
    WelcomeScreenRoute(
        onNavigateToMachineryOrderList = {},
        onWelcomeScreenShow = {}
    )
}
