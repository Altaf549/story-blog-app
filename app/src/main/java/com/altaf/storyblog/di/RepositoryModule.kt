package com.altaf.storyblog.di

import com.altaf.storyblog.data.repository.CategoryRepositoryImpl
import com.altaf.storyblog.data.repository.HomeRepositoryImpl
import com.altaf.storyblog.data.repository.StoryRepositoryImpl
import com.altaf.storyblog.domain.mapper.HomeDataMapper
import com.altaf.storyblog.domain.repository.CategoryRepository
import com.altaf.storyblog.domain.repository.HomeRepository
import com.altaf.storyblog.domain.repository.StoryRepository
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
    abstract fun bindHomeRepository(
        homeRepositoryImpl: HomeRepositoryImpl
    ): HomeRepository
    
    @Binds
    @Singleton
    abstract fun bindCategoryRepository(
        categoryRepositoryImpl: CategoryRepositoryImpl
    ): CategoryRepository

    @Binds
    @Singleton
    abstract fun bindStoryRepository(
        storyRepositoryImpl: StoryRepositoryImpl
    ): StoryRepository
}
