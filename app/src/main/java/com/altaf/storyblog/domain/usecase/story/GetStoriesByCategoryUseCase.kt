package com.altaf.storyblog.domain.usecase.story

import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface GetStoriesByCategoryUseCase {
    suspend operator fun invoke(
        categorySlug: String,
        page: Int = 1,
        perPage: Int = 15
    ): NetworkResult<MutableList<Story>>
}
