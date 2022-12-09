package com.rc.machinerybooker.feature.machineryorderlist.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.rc.machinerybooker.domain.entities.MachineryOrder

@Composable
fun OrderListRoute(
    viewModel: MachineryOrderListViewModel = hiltViewModel()
){
    val currentUiState = viewModel.uiState.collectAsState().value
    OrderListScreen(
        uiState = currentUiState
    )
}

@Composable
fun OrderListScreen(
    uiState: MachineryOrderListState
){
    if (uiState.noMachineryOrdersFound) {
        EmptyOrderListScreen()
    } else {
        OrderListScreen(
            machineryOrders = uiState.machineryOrders,
            isLoading = uiState.isLoading,
            isFaulty = uiState.isFaulty
        )
    }
}

@Composable
fun EmptyOrderListScreen(){

}

@Composable
fun OrderListScreen(
    machineryOrders: List<MachineryOrder>,
    isLoading: Boolean,
    isFaulty: Boolean
){

}
