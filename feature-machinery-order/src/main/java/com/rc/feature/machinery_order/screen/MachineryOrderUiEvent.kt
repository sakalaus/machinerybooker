package com.rc.feature.machinery_order.screen

sealed interface MachineryOrderEvent {
    class VehicleCodeChange(code: String)
    object VehicleSelectFromDropDown
    class ProjectCodeChange(code: String)
    object ProjectSelectFromDropDown
}