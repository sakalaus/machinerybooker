package com.rc.feature.machinery_order.screen

import com.rc.machinerybooker.domain.entities.Vehicle

sealed interface MachineryOrderEvent {
    class VehicleCodeChange(code: String): MachineryOrderEvent
    class ValueSelectFromDropDown(val value: Any): MachineryOrderEvent
    class ProjectCodeChange(code: String): MachineryOrderEvent
}