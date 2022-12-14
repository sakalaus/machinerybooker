package com.rc.machinerybooker.feature.machineryorderlist.screen

import android.graphics.fonts.FontStyle
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.NoAccounts
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.rc.machinerybooker.core.ui.widgets.EmptyScreen
import com.rc.machinerybooker.domain.entities.MachineryOrder
import com.rc.machinerybooker.domain.entities.OrderStatus
import com.rc.machinerybooker.domain.mock.mockExtendedMachineryOrderData
import com.rc.machinerybooker.domain.mock.mockMachineryOrders
import com.rc.machinerybooker.domain.usecases.ExtendedMachineryOrderData
import com.rc.machinerybooker.domain.usecases.extendedMachineryOrderMapType

@Composable
fun OrderListRoute(
    viewModel: MachineryOrderListViewModel = hiltViewModel(),
    welcomeScreenShown: Boolean,
    onNavigateToMachineryOrder: () -> Unit,
    onNavigateToWelcomeScreen: () -> Unit
) {
    val currentUiState = viewModel.uiState.collectAsState().value
    OrderListScreen(
        uiState = currentUiState,
        welcomeScreenShown = welcomeScreenShown,
        onNavigateToMachineryOrder = onNavigateToMachineryOrder,
        onNavigateToWelcomeScreen = onNavigateToWelcomeScreen
    )
}

@Composable
fun OrderListScreen(
    uiState: MachineryOrderListState,
    welcomeScreenShown: Boolean,
    onNavigateToMachineryOrder: () -> Unit,
    onNavigateToWelcomeScreen: () -> Unit
) {
    LaunchedEffect(key1 = true){
        if (welcomeScreenShown.not()){
            onNavigateToWelcomeScreen()
        }
    }
    if (uiState.noMachineryOrdersFound) {
        EmptyOrderListScreen()
    } else {
        OrderListScreen(
            machineryOrdersMap = uiState.machineryOrdersMap,
            isLoading = uiState.isLoading,
            isFaulty = uiState.isFaulty,
            onNavigateToMachineryOrder = onNavigateToMachineryOrder
        )
    }
}

@Composable
fun EmptyOrderListScreen() {
    EmptyScreen(
        imageVector = Icons.Outlined.NoAccounts,
        text = "No machinery orders found"
    )
}

@Composable
fun OrderListScreen(
    machineryOrdersMap: extendedMachineryOrderMapType,
    isLoading: Boolean,
    isFaulty: Boolean,
    onNavigateToMachineryOrder: () -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(machineryOrdersMap.keys.toList()) { machineryOrder ->
            Card(
                shape = RoundedCornerShape(4.dp),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                ),
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .height(96.dp)
                    .clickable {
                        onNavigateToMachineryOrder()
                    }
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    MachineryOrderItem(
                        machineryOrder = machineryOrder,
                        extendedData = machineryOrdersMap[machineryOrder]
                    )
                }
            }
        }
    }
}

@Composable
fun MachineryOrderItem(
    machineryOrder: MachineryOrder,
    extendedData: ExtendedMachineryOrderData?
) {
    extendedData?.let {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            MachineryOrderItemRow(
                text = "12.06.22 14:00-16.00 ${extendedData.vehicle?.description}",
                fontWeight = FontWeight.Bold,
                background = MaterialTheme.colorScheme.tertiaryContainer
            )
            MachineryOrderItemRow(text = "${extendedData.project?.externalId} ${extendedData.project?.description}")
            MachineryOrderItemRowTwoColumns(
                textStart = machineryOrder.location,
                textEnd = "${extendedData.user?.name}",
            )
            MachineryOrderItemRowTwoColumns(
                textStart = stringResource(machineryOrder.status.resId),
                textEnd = "${extendedData.clientDepartment?.description} -> ${extendedData.providerDepartment?.description}",
                colorStart = if (machineryOrder.status == OrderStatus.Cancelled) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.tertiaryContainer
            )
        }
    }
}

@Composable
fun MachineryOrderItemRow(
    text: String,
    background: Color = Color.Transparent,
    fontWeight: FontWeight = FontWeight.Normal
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(modifier = Modifier
            .fillMaxWidth()
            .background(background)
            .wrapContentHeight(),
            text = text,
            fontWeight = fontWeight,
            overflow = TextOverflow.Ellipsis)
    }
}

@Composable
fun MachineryOrderItemRowTwoColumns(
    textStart: String,
    colorStart: Color = Color.Black,
    textEnd: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(24.dp)
            .padding(horizontal = 4.dp, vertical = 2.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(modifier = Modifier
            .weight(0.7f)
            .wrapContentHeight(),
            text = textStart,
            overflow = TextOverflow.Ellipsis,
            color = colorStart
        )
        Spacer(modifier = Modifier.width(2.dp))
        Text(modifier = Modifier
            .weight(0.3f)
            .wrapContentHeight(),
            textAlign = TextAlign.Right,
            text = textEnd,
            overflow = TextOverflow.Ellipsis)
    }
}

@Preview
@Composable
fun MachineryOrderItemPreview() {
    MachineryOrderItem(
        machineryOrder = mockMachineryOrders[0],
        extendedData = mockExtendedMachineryOrderData
    )
}


