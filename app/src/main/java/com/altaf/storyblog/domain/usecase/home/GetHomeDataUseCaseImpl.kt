package com.altaf.storyblog.domain.usecase.home

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
            val data = repository.getHomeData().data
            return if(data != null) {
                NetworkResult.Success(mapper.mapToDomain(data), "Success")
            } else {
                NetworkResult.Error(repository.getHomeData().message ?: "Server Error")
            }
        } catch (e: Exception) {
            return NetworkResult.Error(e.message ?: "Server Error")
        }
    }
}
