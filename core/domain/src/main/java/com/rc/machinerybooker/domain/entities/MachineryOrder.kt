package com.rc.machinerybooker.domain.entities

import androidx.annotation.StringRes
import com.rc.machinerybooker.domain.R as domainRes

data class MachineryOrder(
    val id: Long,
    val externalId: String,
    val description: String,
    val clientDepartmentId: Long,
    val providerDepartmentId: Long,
    val userId: Long,
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

enum class OrderStatus(@StringRes val resId: Int)
{
    Confirmed(resId = domainRes.string.confirmed),
    Cancelled(resId = domainRes.string.cancelled),
    InProgress(resId = domainRes.string.in_progress),
    Completed(resId = domainRes.string.completed),
}
