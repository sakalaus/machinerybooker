package com.rc.machinerybooker.domain.usecases

import com.rc.machinerybooker.domain.entities.Department
import com.rc.machinerybooker.domain.entities.Project
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveDepartments @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Department>> {
        return repository.observeDepartments()
    }
}