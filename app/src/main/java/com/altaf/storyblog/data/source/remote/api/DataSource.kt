package com.altaf.storyblog.data.source.remote.api

import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface DataSource {
    suspend fun getHomeData(): NetworkResult<HomeResponseDto>
}