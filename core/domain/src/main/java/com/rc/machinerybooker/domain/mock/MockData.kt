package com.rc.machinerybooker.domain.mock

import com.rc.machinerybooker.domain.entities.*
import com.rc.machinerybooker.domain.usecases.ExtendedMachineryOrderData

val mockUsers = listOf(
    User(
        id =1,
        externalId = "1",
        name = "Винник О.Л.",
        departmentId = 1,
        role = Role.Client
    ),
    User(
        id = 2,
        externalId = "2",
        name = "Елисеев А.А.",
        departmentId = 2,
        role = Role.Client
    ),
)

val mockDepartments = listOf(
    Department(
        id = 1,
        externalId = "1",
        description = "ВРК1",
        role = Role.Client
    ),
    Department(
        id = 2,
        externalId = "2",
        description = "КС1",
        role = Role.Provider
    ),
    Department(
        id = 3,
        externalId = "3",
        description = "АТЦ1",
        role = Role.Client
    ),
    Department(
        id = 4,
        externalId = "4",
        description = "БС1",
        role = Role.Provider
    )
)

val mockVehicles = listOf(
    Vehicle(
        id = 1,
        externalId = "1",
        departmentId = 2,
        description = "Кран Caterpillar 432423",
        licensePlate = "90943-2"
    ),
    Vehicle(
        id = 2,
        externalId = "2",
        description = "Буксир 102 Наджный",
        departmentId = 2,
        licensePlate = "4343-1"
    ),
)

val mockProjects = listOf(
    Project(
        id = 1,
        externalId = "1",
        description = "Ремонт крыши третьего цеха (кладовая)",
        departmentId = 1
    ),
    Project(
        id = 2,
        externalId = "2",
        description = "Очистка кнехт на причале",
        departmentId = 3
    ),
)


val mockMachineryOrders = listOf(
    MachineryOrder(
        id = 1,
        externalId = "1",
        description = "Lift up some heavy stuff",
        clientDepartmentId = 1,
        providerDepartmentId = 2,
        userId = 1,
        status = OrderStatus.Confirmed,
        vehicleId = 1,
        projectId = 1,
        location = "Factory 3D aisle 4th Row",
        createdTimeStamp = 1670496670,
        dateTimeStamp = 1670496910,
        plannedStartTimeStamp = 1670669710,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1670680510,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 2,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 3,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 4,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 5,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 6,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 7,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
    MachineryOrder(
        id = 8,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 2,
        location = "Peer 3 Exit Left",
        createdTimeStamp = 1668088510,
        dateTimeStamp = 1671026110,
        plannedStartTimeStamp = 1671036910,
        actualStartTimeStamp = 0,
        plannedFinishTimeStamp = 1671051310,
        actualFinishTimeStamp = 0,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0
    ),
)

val mockExtendedMachineryOrderData = ExtendedMachineryOrderData(
    clientDepartment = mockDepartments[0],
    providerDepartment = mockDepartments[1],
    vehicle = mockVehicles[0],
    project = mockProjects[0],
    user = mockUsers[0]
)