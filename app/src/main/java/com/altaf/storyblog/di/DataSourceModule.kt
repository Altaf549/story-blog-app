package com.altaf.storyblog.di

import com.altaf.storyblog.data.source.remote.api.ApiService
import com.altaf.storyblog.data.source.remote.api.DataSource
import com.altaf.storyblog.data.source.remote.api.DataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideDataSource(apiService: ApiService): DataSource {
        return DataSourceImpl(apiService)
    }
}
