package com.rc.machinerybooker.domain.entities

data class Vehicle(
    val id: Long,
    val externalId: String,
    val description: String,
    val departmentId: Long,
    val licensePlate: String
)

fun Any?.representation(): String = this?.let {
    when (it) {
        is Vehicle -> "${it.externalId} ${it.description}"
        is Project -> "${it.externalId} ${it.description}"
        is Department -> it.description
        else -> it.toString()
    }
} ?: ""
