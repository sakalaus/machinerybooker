package com.rc.machinerybooker.domain.usecases

import com.rc.machinerybooker.domain.entities.Vehicle
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveVehicles @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Vehicle>> {
        return repository.observeVehicles()
    }
}