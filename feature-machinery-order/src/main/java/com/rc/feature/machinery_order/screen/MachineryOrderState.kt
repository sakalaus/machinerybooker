package com.rc.feature.machinery_order.screen

import com.rc.machinerybooker.core.utils.toLocalDateTime
import com.rc.machinerybooker.core.utils.toLocalDateTimeUTC
import com.rc.machinerybooker.domain.entities.Department
import com.rc.machinerybooker.domain.entities.OrderStatus
import com.rc.machinerybooker.domain.entities.Project
import com.rc.machinerybooker.domain.entities.Vehicle
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class MachineryOrderState(
    val id: Long = 0L,
    val externalId: String = "",
    val description: String = "",
    val departmentOptions: List<Department> = emptyList(),
    val clientDepartment: Department? = null,
    val providerDepartment: Department? = null,
    val status: OrderStatus = OrderStatus.Draft,
    val vehicleOptions: List<Vehicle> = emptyList(),
    val vehicle: Vehicle? = null,
    val projectOptions: List<Project> = emptyList(),
    val project: Project? = null,
    val locationDescription: String = "",
    val userId: Long = 0L,
    val createdTimeStamp: Long = 0L,
    val plannedStartTimeStamp: Long = 0L,
    val actualClientStartOnTime: Boolean = false,
    val actualClientStartTimeStamp: Long = 0L,
    val actualProviderStartOnTime: Boolean = false,
    val actualProviderStartTimeStamp: Long = 0L,
    val plannedFinishTimeStamp: Long = 0L,
    val actualClientFinishOnTime: Boolean = false,
    val actualClientFinishTimeStamp: Long = 0L,
    val actualProviderFinishOnTime: Boolean = false,
    val actualProviderFinishTimeStamp: Long = 0L,
    val leftBaseTimeStamp: Long = 0L,
    val returnedToBaseTimeStamp: Long = 0L
){

    val plannedStartLocalDateTime: LocalDateTime
        get() = plannedStartTimeStamp.toLocalDateTime()
    val plannedStartDateString: String
        get() = plannedStartTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
    val plannedStartHoursMinutesString: String
        get() = plannedStartTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))

    val plannedFinishLocalDateTime: LocalDateTime
        get() = plannedFinishTimeStamp.toLocalDateTime()
    val plannedFinishDateString: String
        get() = plannedFinishTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
    val plannedFinishHoursMinutesString: String
        get() = plannedFinishTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))

    val actualClientStartLocalDateTime: LocalDateTime
        get() = actualClientStartTimeStamp.toLocalDateTime()
    val actualClientStartDateString: String
        get() = actualClientStartTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
    val actualClientStartHoursMinutesString: String
        get() = actualClientStartTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))

    val actualProviderStartLocalDateTime: LocalDateTime
        get() = actualProviderStartTimeStamp.toLocalDateTime()
    val actualProviderStartDateString: String
        get() = actualProviderStartTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
    val actualProviderStartHoursMinutesString: String
        get() = actualProviderStartTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))
    
    val actualClientFinishLocalDateTime: LocalDateTime
        get() = actualClientFinishTimeStamp.toLocalDateTime()
    val actualClientFinishStartDateString: String
        get() = actualClientFinishTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
    val actualClientFinishHoursMinutesString: String
        get() = actualClientFinishTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))

    val actualProviderFinishLocalDateTime: LocalDateTime
        get() = actualProviderFinishTimeStamp.toLocalDateTime()
    val actualProviderFinishDateString: String
        get() = actualProviderFinishTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"))
    val actualProviderFinishHoursMinutesString: String
        get() = actualProviderFinishTimeStamp.toLocalDateTime().format(DateTimeFormatter.ofPattern("HH:mm"))

    val plannedDuration: Long
        get() = plannedFinishTimeStamp - plannedStartTimeStamp
    val plannedDurationLocalDateTime: LocalDateTime
        get() = (plannedFinishTimeStamp - plannedStartTimeStamp).toLocalDateTimeUTC()
    val plannedDurationHoursMinutesString: String
        get() = (plannedFinishTimeStamp - plannedStartTimeStamp).toLocalDateTimeUTC().format(DateTimeFormatter.ofPattern("HH:mm"))

    val actualClientDuration: Long
        get() = actualClientFinishTimeStamp - actualClientStartTimeStamp
    val actualClientDurationLocalDateTime: LocalDateTime
        get() = (actualClientFinishTimeStamp - actualClientStartTimeStamp).toLocalDateTimeUTC()
    val actualClientDurationHoursMinutesString: String
        get() = (actualClientFinishTimeStamp - actualClientStartTimeStamp).toLocalDateTimeUTC().format(DateTimeFormatter.ofPattern("HH:mm"))

    val actualProviderDuration: Long
        get() = actualProviderFinishTimeStamp - actualProviderStartTimeStamp
    val actualProviderDurationLocalDateTime: LocalDateTime
        get() = (actualProviderFinishTimeStamp - actualProviderStartTimeStamp).toLocalDateTimeUTC()
    val actualProviderDurationHoursMinutesString: String
        get() = (actualProviderFinishTimeStamp - actualProviderStartTimeStamp).toLocalDateTimeUTC().format(DateTimeFormatter.ofPattern("HH:mm"))
    
}
