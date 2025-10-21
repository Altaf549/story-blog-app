package com.altaf.storyblog.domain.repository

import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.HomeData
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface HomeRepository {
    suspend fun getHomeData(): NetworkResult<HomeResponseDto>
}
