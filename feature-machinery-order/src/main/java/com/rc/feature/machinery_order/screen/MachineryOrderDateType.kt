package com.rc.feature.machinery_order.screen

sealed interface MachineryOrderDateType{
    object StartPlanned: MachineryOrderDateType
    object StartActualClient: MachineryOrderDateType
    object StartActualProvider: MachineryOrderDateType
    object FinishPlanned: MachineryOrderDateType
    object FinishActualClient: MachineryOrderDateType
    object FinishActualProvider: MachineryOrderDateType
}