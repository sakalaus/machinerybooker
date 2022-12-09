package com.rc.machinerybooker.feature.machineryorderlist.screen

import com.rc.machinerybooker.domain.usecases.extendedMachineryOrderMapType

data class MachineryOrderListState(
    val isLoading: Boolean = false,
    val isFaulty: Boolean = false,
    val machineryOrdersMap: extendedMachineryOrderMapType = emptyMap()
){
    val noMachineryOrdersFound
    get() = machineryOrdersMap.isEmpty()
}
