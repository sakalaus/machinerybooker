package com.rc.feature.machinery_order.screen

sealed interface MachineryOrderDateType{
    object StartPlanned: MachineryOrderDateType
    object StartActualClient: MachineryOrderDateType
    object StartActualVendor: MachineryOrderDateType
    object FinishPlanned: MachineryOrderDateType
    object FinishActualClient: MachineryOrderDateType
    object FinishActualVendor: MachineryOrderDateType
}