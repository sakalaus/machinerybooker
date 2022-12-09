package com.rc.machinerybooker.domain.usecases

import com.rc.machinerybooker.domain.entities.*
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

typealias extendedMachineryOrderMapType = Map<MachineryOrder, ExtendedMachineryOrderData>

class ObserveMachineryOrderExtendedDataList @Inject constructor(private val repository: Repository) {
    operator fun invoke(machineryOrderFilter: MachineryOrderFilter): Flow<extendedMachineryOrderMapType> {
        return repository.observeMachineryOrderList().map { machineryOrderList ->
            machineryOrderList.associateWith { machineryOrder ->
                ExtendedMachineryOrderData(
                    clientDepartment = repository.getDepartment(departmentId = machineryOrder.clientDepartmentId),
                    providerDepartment = repository.getDepartment(departmentId = machineryOrder.providerDepartmentId),
                    vehicle = repository.getVehicle(vehicleId = machineryOrder.vehicleId),
                    project = repository.getProject(projectId = machineryOrder.projectId),
                    user = repository.getUser(userId = machineryOrder.userId)
                )
            }
        }
    }
}

data class ExtendedMachineryOrderData(
    val clientDepartment: Department?,
    val providerDepartment: Department?,
    val vehicle: Vehicle?,
    val project: Project?,
    val user: User?
)
