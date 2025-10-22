package com.altaf.storyblog.domain.usecase.home

import android.util.Log
import com.altaf.storyblog.domain.mapper.HomeDataMapper
import com.altaf.storyblog.domain.model.HomeData
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.HomeRepository
import javax.inject.Inject

class GetHomeDataUseCaseImpl @Inject constructor(
    private val repository: HomeRepository,
    private val mapper: HomeDataMapper
) : GetHomeDataUseCase {

    override suspend fun invoke(): NetworkResult<HomeData> {
        try {
            val result = repository.getHomeData()
            return if (result.data != null) {
                val mappedData = mapper.mapToDomain(result.data)
                NetworkResult.Success(mappedData, "Success")
            } else {
                val errorMsg = result.message ?: "Server Error"
                NetworkResult.Error(errorMsg)
            }
        } catch (e: Exception) {
            val errorMsg = e.message ?: "Unknown error occurred"
            e.printStackTrace()
            return NetworkResult.Error(errorMsg)
        }
    }
}
