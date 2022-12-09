package com.rc.machinerybooker.domain.entities

data class MachineryOrderFilter(
    val userIds: List<Long> = emptyList(),
    val clientDepartmentIds: List<Long> = emptyList(),
    val providerDepartmentIds: List<Long> = emptyList(),
    val timeStampAfter: Long? = null,
    val timeStampBefore: Long? = null
)