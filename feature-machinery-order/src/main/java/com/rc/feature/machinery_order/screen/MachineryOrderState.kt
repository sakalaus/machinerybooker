package com.rc.feature.machinery_order.screen

import com.rc.machinerybooker.domain.entities.OrderStatus

data class MachineryOrderState(
    val id: Long = 0L,
    val externalId: String = "",
    val description: String = "",
    val clientDepartmentId: Long = 0L,
    val providerDepartmentId: Long = 0L,
    val userId: Long = 0L,
    val status: OrderStatus = OrderStatus.Draft,
    val vehicleId: Long = 0L,
    val projectId: Long = 0L,
    val locationDescription: String = "",
    val createdTimeStamp: Long = 0L,
    val dateTimeStamp: Long = 0L,
    val plannedStartTimeStamp: Long = 0L,
    val actualStartTimeStamp: Long = 0L,
    val plannedFinishTimeStamp: Long = 0L,
    val actualFinishTimeStamp: Long = 0L,
    val leftBaseTimeStamp: Long = 0L,
    val returnedToBaseTimeStamp: Long = 0L
)
