package com.rc.machinerybooker.domain.entities

data class Project(
    val id: Long,
    val externalId: String,
    val description: String,
    val departmentId: Long
)
