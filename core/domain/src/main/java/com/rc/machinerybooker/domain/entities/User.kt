package com.rc.machinerybooker.domain.entities

data class User(
    val id: Long,
    val externalId: String,
    val name: String,
    val departmentId: Long,
    val role: Role
)

enum class Role{
    Client,
    Provider,
    Superuser
}