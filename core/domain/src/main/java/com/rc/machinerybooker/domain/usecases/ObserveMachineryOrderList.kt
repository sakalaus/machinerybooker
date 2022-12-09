package com.rc.machinerybooker.domain.usecases

import com.rc.machinerybooker.domain.entities.MachineryOrder
import com.rc.machinerybooker.domain.entities.MachineryOrderFilter
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMachineryOrderList @Inject constructor(private val repository: Repository) {
    operator fun invoke(machineryOrderFilter: MachineryOrderFilter): Flow<List<MachineryOrder>> {
        return repository.observeMachineryOrderList()
    }
}