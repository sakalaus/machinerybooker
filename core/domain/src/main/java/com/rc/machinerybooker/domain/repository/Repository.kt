package com.rc.machinerybooker.domain.repository

import com.rc.machinerybooker.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun observeMachineryOrderList(machineryOrderFilter: MachineryOrderFilter): Flow<List<MachineryOrder>>
    fun observeVehicles(): Flow<List<Vehicle>>
    fun observeProjects(): Flow<List<Project>>
    fun observeDepartments(): Flow<List<Department>>
    fun getMachineryOrder(orderId: Long): MachineryOrder?
    fun getProject(projectId: Long): Project?
    fun getDepartment(departmentId: Long): Department?
    fun getVehicle(vehicleId: Long): Vehicle?
    fun getUser(userId: Long): User?
    fun observeMachineryOrder(machineryOrderId: Long): Flow<MachineryOrder>
}