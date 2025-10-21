package com.altaf.storyblog.di

import com.altaf.storyblog.domain.mapper.HomeDataMapper
import com.altaf.storyblog.domain.repository.HomeRepository
import com.altaf.storyblog.domain.usecase.home.GetHomeDataUseCase
import com.altaf.storyblog.domain.usecase.home.GetHomeDataUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun provideGetHomeDataUseCase(homeRepository: HomeRepository, mapper: HomeDataMapper): GetHomeDataUseCase = GetHomeDataUseCaseImpl(homeRepository, mapper)
}