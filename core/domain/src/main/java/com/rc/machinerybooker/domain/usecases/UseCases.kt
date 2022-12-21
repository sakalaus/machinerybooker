package com.rc.machinerybooker.domain.usecases

data class UseCases(
    val observeMachineryOrderExtendedDataList: ObserveMachineryOrderExtendedDataList,
    val observeMachineryOrder: ObserveMachineryOrder,
    val observeVehicles: ObserveVehicles,
    val observeProjects: ObserveProjects,
    val observeDepartments: ObserveDepartments,
)