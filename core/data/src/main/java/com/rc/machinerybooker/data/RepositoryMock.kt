package com.rc.machinerybooker.data

import com.rc.machinerybooker.domain.entities.*
import com.rc.machinerybooker.domain.mock.*
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class RepositoryMock @Inject constructor(): Repository {

    override fun observeMachineryOrderList(): Flow<List<MachineryOrder>> = flowOf(mockMachineryOrders)
    override fun observeVehicles(): Flow<List<Vehicle>> = flowOf(mockVehicles)
    override fun observeProjects(): Flow<List<Project>> = flowOf(mockProjects)
    override fun observeDepartments(): Flow<List<Department>> = flowOf(mockDepartments)

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
