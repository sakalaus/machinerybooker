package com.rc.machinerybooker.feature.machineryorderlist.screen

import com.rc.machinerybooker.domain.entities.MachineryOrder

data class MachineryOrderListState(
    val machineryOrders: List<MachineryOrder>
)
