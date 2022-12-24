package com.rc.feature.machinery_order.screen

import com.rc.machinerybooker.domain.entities.Vehicle
import java.time.LocalDate

sealed interface MachineryOrderEvent {
    class VehicleCodeChange(code: String): MachineryOrderEvent
    class ValueSelectFromDropDown(val value: Any): MachineryOrderEvent
    class DateSelect(val value: LocalDate, val dateType: MachineryOrderDateType): MachineryOrderEvent
    class ProjectCodeChange(code: String): MachineryOrderEvent
}