package com.rc.machinerybooker.di

import com.rc.machinerybooker.data.RepositoryMock
import com.rc.machinerybooker.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRepository(
        repository: RepositoryMock)
    : Repository
}

