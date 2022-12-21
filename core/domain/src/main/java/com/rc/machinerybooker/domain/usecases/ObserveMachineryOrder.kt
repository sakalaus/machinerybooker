package com.rc.machinerybooker.domain.usecases

import com.rc.machinerybooker.domain.entities.MachineryOrder
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveMachineryOrder @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(
        machineryOrderId: Long
    ): Flow<MachineryOrder> {
        return repository.observeMachineryOrder(
            machineryOrderId = machineryOrderId
        )
    }
}