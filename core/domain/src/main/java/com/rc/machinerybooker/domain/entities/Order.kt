package com.rc.machinerybooker.domain.entities

data class Order(
    val id: Long,
    val externalId: String,
    val description: String,
    val clientDepartmentId: Long,
    val providerDepartmentId: Long,
    val createdBy: Long,
    val status: OrderStatus,
    val vehicleId: Long,
    val projectId: Long,
    val location: String,
    val createdTimeStamp: Long,
    val dateTimeStamp: Long,
    val plannedStartTimeStamp: Long,
    val actualStartTimeStamp: Long,
    val plannedFinishTimeStamp: Long,
    val actualFinishTimeStamp: Long,
    val leftBaseTimeStamp: Long,
    val returnedToBaseTimeStamp: Long
){

    val plannedDuration
    get() = plannedFinishTimeStamp - plannedStartTimeStamp

    val actualDuration
        get() = actualFinishTimeStamp - actualStartTimeStamp
}

enum class OrderStatus{
    Confirmed,
    Cancelled,
    InProgress,
    Completed
}
