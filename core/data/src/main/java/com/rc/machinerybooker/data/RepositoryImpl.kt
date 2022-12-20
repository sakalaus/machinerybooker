package com.rc.machinerybooker.data

import com.rc.machinerybooker.domain.entities.*
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow

class RepositoryImpl: Repository {
    override fun observeMachineryOrderList(): Flow<List<MachineryOrder>> {
        TODO("Not yet implemented")
    }

    override fun observeVehicles(): Flow<List<Vehicle>> {
        TODO("Not yet implemented")
    }

    override fun observeProjects(): Flow<List<Project>> {
        TODO("Not yet implemented")
    }

    override fun observeDepartments(): Flow<List<Department>> {
        TODO("Not yet implemented")
    }

    override fun getMachineryOrder(orderId: Long): MachineryOrder {
        TODO("Not yet implemented")
    }

    override fun getProject(projectId: Long): Project? {
        TODO("Not yet implemented")
    }

    override fun getDepartment(departmentId: Long): Department? {
        TODO("Not yet implemented")
    }

    override fun getVehicle(vehicleId: Long): Vehicle? {
        TODO("Not yet implemented")
    }

    override fun getUser(userId: Long): User? {
        TODO("Not yet implemented")
    }
}