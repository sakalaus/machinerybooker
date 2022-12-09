package com.rc.machinerybooker.domain.repository

import com.rc.machinerybooker.domain.entities.*
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun observeMachineryOrderList(): Flow<List<MachineryOrder>>
    fun getMachineryOrder(orderId: Long): MachineryOrder?
    fun getProject(projectId: Long): Project?
    fun getDepartment(departmentId: Long): Department?
    fun getVehicle(vehicleId: Long): Vehicle?
    fun getUser(userId: Long): User?
}