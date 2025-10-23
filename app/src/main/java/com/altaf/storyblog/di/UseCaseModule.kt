package com.altaf.storyblog.di

import com.altaf.storyblog.domain.mapper.CategoryMapper
import com.altaf.storyblog.domain.mapper.HomeDataMapper
import com.altaf.storyblog.domain.mapper.HomeDataMapperImpl
import com.altaf.storyblog.domain.repository.CategoryRepository
import com.altaf.storyblog.domain.repository.HomeRepository
import com.altaf.storyblog.domain.usecase.category.GetCategoriesUseCase
import com.altaf.storyblog.domain.usecase.category.GetCategoriesUseCaseImpl
import com.altaf.storyblog.domain.usecase.home.GetHomeDataUseCase
import com.altaf.storyblog.domain.usecase.home.GetHomeDataUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@InstallIn(SingletonComponent::class)
@Module
abstract class UseCaseModule {
    
    companion object {
        @Provides
        @Singleton
        fun provideGetHomeDataUseCase(
            homeRepository: HomeRepository, 
            mapper: HomeDataMapper
        ): GetHomeDataUseCase = GetHomeDataUseCaseImpl(homeRepository, mapper)
        
        @Provides
        @Singleton
        fun provideGetCategoriesUseCase(
            categoryRepository: CategoryRepository,
            mapper: CategoryMapper
        ): GetCategoriesUseCase = GetCategoriesUseCaseImpl(categoryRepository, mapper)
    }
}