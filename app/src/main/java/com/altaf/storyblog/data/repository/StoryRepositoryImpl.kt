package com.altaf.storyblog.data.repository

import com.altaf.storyblog.data.source.remote.api.DataSource
import com.altaf.storyblog.data.source.remote.dto.CategoryStoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.StoriesResponseDto
import com.altaf.storyblog.domain.mapper.StoryMapper
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.StoryRepository
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : StoryRepository {

    override suspend fun getStories(page: Int, perPage: Int): NetworkResult<StoriesResponseDto> = dataSource.getStories(page, perPage)
    
    override suspend fun getStoriesByCategory(slug: String, page: Int, perPage: Int): NetworkResult<CategoryStoriesResponseDto> =
        dataSource.getStoriesByCategory(slug, page, perPage)
}
