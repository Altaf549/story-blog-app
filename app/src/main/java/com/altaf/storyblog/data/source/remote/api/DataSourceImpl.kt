package com.altaf.storyblog.data.source.remote.api

import android.util.Log
import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

class DataSourceImpl(
    private val apiService: ApiService
) : DataSource {

    override suspend fun getHomeData(): NetworkResult<HomeResponseDto> {
        return try {
            val data = apiService.getHomeData()
            NetworkResult.Success(data, "Success")
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.Error("Failed to fetch home data: ${e.message}")
        }
    }

    override suspend fun getCategories(): NetworkResult<CategoriesResponseDto> {
        return try {
            val data = apiService.getCategories()
            NetworkResult.Success(data, "Categories fetched successfully")
        } catch (e: Exception) {
            e.printStackTrace()
            NetworkResult.Error("Failed to fetch categories: ${e.message}")
        }
    }
}