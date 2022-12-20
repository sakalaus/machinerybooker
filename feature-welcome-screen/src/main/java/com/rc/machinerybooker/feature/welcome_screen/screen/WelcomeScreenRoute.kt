package com.rc.machinerybooker.feature.welcome_screen.screen

import android.view.animation.OvershootInterpolator
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
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
    val scale = remember {
        androidx.compose.animation.core.Animatable(0f)
    }
    LaunchedEffect(key1 = true) {
        scale.animateTo(
            targetValue = 1f,
            animationSpec = tween(
                durationMillis = 2000,
                easing = {
                    OvershootInterpolator(2f).getInterpolation(it)
                }
            )
        )
        delay(1000L)
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
                .scale(scale.value)
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
