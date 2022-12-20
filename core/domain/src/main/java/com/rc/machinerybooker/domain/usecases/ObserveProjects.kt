package com.rc.machinerybooker.domain.usecases

import com.rc.machinerybooker.domain.entities.Project
import com.rc.machinerybooker.domain.repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveProjects @Inject constructor(
    private val repository: Repository
) {
    operator fun invoke(): Flow<List<Project>> {
        return repository.observeProjects()
    }
}