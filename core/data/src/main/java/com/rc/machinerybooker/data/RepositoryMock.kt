package com.rc.machinerybooker.data

import com.rc.machinerybooker.domain.entities.*
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepositoryMock @Inject constructor(): Repository {

    override fun observeMachineryOrderList(): Flow<List<MachineryOrder>> = flowOf(mockMachineryOrders)

    override fun getMachineryOrder(orderId: Long): MachineryOrder? = mockMachineryOrders.firstOrNull {
        it.id == orderId
    }

    override fun getProject(projectId: Long): Project?  = mockProjects.firstOrNull{
        it.id == projectId
    }

    override fun getDepartment(departmentId: Long): Department? = mockDepartments.firstOrNull{
        it.id == departmentId
    }

    override fun getVehicle(vehicleId: Long): Vehicle? = mockVehicles.firstOrNull{
        it.id == vehicleId
    }

    override fun getUser(userId: Long): User? = mockUsers.firstOrNull {
        it.id == userId
    }
}

val mockUsers = listOf(
    User(
        id =1,
        externalId = "1",
        name = "Nick Brown",
        departmentId = 1,
        role = Role.Client
    ),
    User(
        id = 2,
        externalId = "2",
        name = "Annie Howard",
        departmentId = 2,
        role = Role.Client
    ),
)

val mockDepartments = listOf(
    Department(
        id = 1,
        externalId = "1",
        description = "VRK 1",
        role = Role.Client
    ),
    Department(
        id = 2,
        externalId = "2",
        description = "KS 1",
        role = Role.Provider
    ),
    Department(
        id = 3,
        externalId = "3",
        description = "ATC 1",
        role = Role.Client
    ),
    Department(
        id = 4,
        externalId = "4",
        description = "BS 1",
        role = Role.Provider
    )
)

val mockVehicles = listOf(
    Vehicle(
        id = 1,
        externalId = "1",
        departmentId = 2,
        licensePlate = "90943-2"
    ),
    Vehicle(
        id = 2,
        externalId = "2",
        departmentId = 2,
        licensePlate = "4343-1"
    ),
)

val mockProjects = listOf(
    Project(
        id = 1,
        externalId = "1",
        description = "Factory repairs",
        departmentId = 1
    ),
    Project(
        id = 2,
        externalId = "2",
        description = "Peer scrub",
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
        createdByUserId = 1,
        status = OrderStatus.Confirmed,
        vehicleId = 1,
        projectId = 0,
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
        createdByUserId = 1,
        status = OrderStatus.Cancelled,
        vehicleId = 1,
        projectId = 0,
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