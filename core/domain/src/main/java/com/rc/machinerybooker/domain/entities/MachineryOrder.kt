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
    val actualClientStartTimeStamp: Long,
    val actualProviderStartTimeStamp: Long,
    val plannedFinishTimeStamp: Long,
    val actualClientFinishTimeStamp: Long,
    val actualProviderFinishTimeStamp: Long,
    val leftBaseTimeStamp: Long,
    val returnedToBaseTimeStamp: Long
){

    val plannedDuration
    get() = plannedFinishTimeStamp - plannedStartTimeStamp

    val actualClientDuration
        get() = actualClientFinishTimeStamp - actualClientStartTimeStamp

    val actualProviderDuration
        get() = actualProviderFinishTimeStamp - actualProviderStartTimeStamp
}

enum class OrderStatus(@StringRes val resId: Int)
{
    Confirmed(resId = domainRes.string.confirmed),
    Cancelled(resId = domainRes.string.cancelled),
    Draft(resId = domainRes.string.draft),
    InProgress(resId = domainRes.string.in_progress),
    Completed(resId = domainRes.string.completed),
}
