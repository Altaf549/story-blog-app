package com.altaf.storyblog.di

import com.altaf.storyblog.domain.mapper.CategoryMapper
import com.altaf.storyblog.domain.mapper.CategoryMapperImpl
import com.altaf.storyblog.domain.mapper.HomeDataMapper
import com.altaf.storyblog.domain.mapper.HomeDataMapperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MapperModule {

    @Binds
    @Singleton
    abstract fun bindHomeDataMapper(impl: HomeDataMapperImpl): HomeDataMapper
    
    @Binds
    @Singleton
    abstract fun bindCategoryMapper(impl: CategoryMapperImpl): CategoryMapper
}
