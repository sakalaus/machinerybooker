package com.rc.machinerybooker.feature.machineryorderlist.screen

import com.rc.machinerybooker.domain.entities.MachineryOrder

data class MachineryOrderListState(
    val isLoading: Boolean = false,
    val isFaulty: Boolean = false,
    val machineryOrders: List<MachineryOrder> = emptyList()
){
    val noMachineryOrdersFound
    get() = machineryOrders.isEmpty()
}
