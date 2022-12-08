package com.rc.machinerybooker.domain.entities

data class User(
    val id: Long,
    val externalId: String,
    val name: String,
    val department: Department,
    val role: UserRole
)

enum class UserRole{
    Client,
    Provider,
    Superuser
}