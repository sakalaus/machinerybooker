package com.rc.machinerybooker.feature.machineryorderlist.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun OrderListRoute(
    viewModel: MachineryOrderListViewModel = hiltViewModel()
){
    OrderListScreen()
}

@Composable
fun OrderListScreen(){
    Text(
        modifier = Modifier.fillMaxWidth(),
        text = "Machinery order list"
    )
}