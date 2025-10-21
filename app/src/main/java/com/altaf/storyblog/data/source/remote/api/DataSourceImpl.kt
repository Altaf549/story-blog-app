package com.altaf.storyblog.data.source.remote.api

import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

class DataSourceImpl(
    private val apiService: ApiService
) : DataSource {

    override suspend fun getHomeData(): NetworkResult<HomeResponseDto> {
        return try {
            apiService.getHomeData()
        } catch (e: Exception) {
            NetworkResult.Error("Failed to fetch home data: ${e.message}")
        }
    }
}