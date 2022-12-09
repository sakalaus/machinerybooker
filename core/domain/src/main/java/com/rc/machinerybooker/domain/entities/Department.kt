package com.rc.machinerybooker.domain.entities

data class Department(
    val id: Long,
    val externalId: String,
    val description: String,
    val role: Role
)
