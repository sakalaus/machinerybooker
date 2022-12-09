package com.rc.machinerybooker.di

import com.rc.machinerybooker.domain.repository.Repository
import com.rc.machinerybooker.domain.usecases.ObserveMachineryOrderList
import com.rc.machinerybooker.domain.usecases.UseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {
    @Provides
    @Singleton
    fun provideUseCases (repository: Repository): UseCases = UseCases(
        observeMachineryOrderList = ObserveMachineryOrderList(repository = repository)
    )
}