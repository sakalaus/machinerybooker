package com.rc.feature.machinery_order.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.rc.machinerybooker.domain.entities.MachineryOrder

@Composable
fun MachineryOrderRoute(
    viewModel: MachineryOrderViewModel = hiltViewModel()
){
    MachineryOrderScreen()
}

@Composable
fun MachineryOrderScreen() {
    Column() {
        Text("text")
    }
}
