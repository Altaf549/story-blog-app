package com.altaf.storyblog.domain.usecase.home

import com.altaf.storyblog.domain.model.HomeData
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface GetHomeDataUseCase {
    suspend operator fun invoke(): NetworkResult<HomeData>
}
