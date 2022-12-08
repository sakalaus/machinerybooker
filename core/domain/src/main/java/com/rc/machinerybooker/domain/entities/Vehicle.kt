package com.rc.machinerybooker.domain.entities

data class Vehicle(
    val id: Long,
    val externalId: String,
    val departmentId: Long,
    val licensePlate: String
)
