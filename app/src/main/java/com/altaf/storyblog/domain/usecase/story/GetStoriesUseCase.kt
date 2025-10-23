package com.altaf.storyblog.domain.usecase.story

import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface GetStoriesUseCase {
    suspend operator fun invoke(page: Int = 1, perPage: Int = 15): NetworkResult<MutableList<Story>>
}
