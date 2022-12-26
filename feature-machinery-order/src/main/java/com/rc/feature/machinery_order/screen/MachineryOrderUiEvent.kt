package com.rc.feature.machinery_order.screen

import java.time.LocalDateTime

sealed interface MachineryOrderEvent {
    class VehicleCodeChange(code: String): MachineryOrderEvent
    class ValueSelectFromDropDown(val value: Any): MachineryOrderEvent
    class DateSelect(val value: LocalDateTime, val dateType: MachineryOrderDateType): MachineryOrderEvent
    class ConfirmOnTime(val checked: Boolean, val dateType: MachineryOrderDateType): MachineryOrderEvent
    class ProjectCodeChange(code: String): MachineryOrderEvent
}