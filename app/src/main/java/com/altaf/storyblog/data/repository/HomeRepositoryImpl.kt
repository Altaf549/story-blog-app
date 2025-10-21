package com.altaf.storyblog.data.repository

import com.altaf.storyblog.data.source.remote.api.DataSource
import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.HomeRepository
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : HomeRepository {
    override suspend fun getHomeData() = dataSource.getHomeData()
}
