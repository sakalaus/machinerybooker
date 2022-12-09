package com.rc.machinerybooker.feature.machineryorderlist.screen

import com.rc.machinerybooker.domain.entities.MachineryOrder

data class MachineryOrderListState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val machineryOrders: List<MachineryOrder> = emptyList()
)
