package com.rc.machinerybooker.feature.machineryorderlist.screen

sealed interface MachineryOrderEvent{
    class MachineryOrderClicked(val machineryOrderId: Long) : MachineryOrderEvent
}
