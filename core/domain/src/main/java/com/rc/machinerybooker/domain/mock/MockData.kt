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
        description = "Буксир №102 Надежный",
        departmentId = 2,
        licensePlate = "4343-1"
    ),
)

val mockProjects = listOf(
    Project(
        id = 1,
        externalId = "1321321313",
        description = "Ремонт крыши третьего цеха (кладовая)",
        departmentId = 1
    ),
    Project(
        id = 2,
        externalId = "2312321312",
        description = "Маневрирование у входа в порт и швартовка у причала",
        departmentId = 3
    ),
    Project(
        id = 3,
        externalId = "1321321332",
        description = "Погрузка паллет",
        departmentId = 1
    ),
    Project(
        id = 4,
        externalId = "2455454354",
        description = "Лоцманские работы",
        departmentId = 3
    ),
    Project(
        id = 5,
        externalId = "1432424234234",
        description = "Перенос балок с судна А на судбно Б",
        departmentId = 1
    ),
    Project(
        id = 6,
        externalId = "2656546546665",
        description = "Буксировка баржи к причалу",
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
        location = "Помещение А3 ряд 12",
        createdTimeStamp = 1671608643000,
        dateTimeStamp = 1671608643000,
        plannedStartTimeStamp = 1671608643000, // 21/12/2022, 09:44:03
        actualClientStartTimeStamp = 1671609543000, // 21/12/2022, 09:59:03
        plannedFinishTimeStamp = 1671613503000,
        actualClientFinishTimeStamp = 1671613623000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1671609363000, // 21/12/2022, 09:56:03
        actualProviderFinishTimeStamp = 1671613563000
    ),
    MachineryOrder(
        id = 2,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Confirmed,
        vehicleId = 2,
        projectId = 2,
        location = "Пристань 3, причал 10",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
    ),
    MachineryOrder(
        id = 3,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 1,
        providerDepartmentId = 2,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 3,
        location = "Пожарный проезд",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
    ),
    MachineryOrder(
        id = 4,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Confirmed,
        vehicleId = 2,
        projectId = 4,
        location = "Бухта Медвежий Угол",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
    ),
    MachineryOrder(
        id = 5,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 1,
        providerDepartmentId = 2,
        userId = 1,
        status = OrderStatus.Confirmed,
        vehicleId = 1,
        projectId = 5,
        location = "Парковка у офисного здания",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
    ),
    MachineryOrder(
        id = 6,
        externalId = "2",
        description = "Escort the bulk ship to the border",
        clientDepartmentId = 3,
        providerDepartmentId = 4,
        userId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 2,
        projectId = 6,
        location = "Речной торговый порт",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
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
        location = "Парковка у офисного здания",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
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
        location = "Парковка у офисного здания",
        createdTimeStamp = 1668088510000,
        dateTimeStamp = 1671026110000,
        plannedStartTimeStamp = 1671036910000,
        actualClientStartTimeStamp = 1672016487000,
        plannedFinishTimeStamp = 1671051310000,
        actualClientFinishTimeStamp = 1671051310000,
        leftBaseTimeStamp = 0,
        returnedToBaseTimeStamp = 0,
        actualProviderStartTimeStamp = 1672026487000,
        actualProviderFinishTimeStamp = 1672036487000
    ),
)

val mockExtendedMachineryOrderData = ExtendedMachineryOrderData(
    clientDepartment = mockDepartments[0],
    providerDepartment = mockDepartments[1],
    vehicle = mockVehicles[0],
    project = mockProjects[0],
    user = mockUsers[0]
)