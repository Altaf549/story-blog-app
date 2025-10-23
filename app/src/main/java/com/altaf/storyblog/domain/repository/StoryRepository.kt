package com.altaf.storyblog.domain.repository

import com.altaf.storyblog.data.source.remote.dto.CategoryStoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.StoriesResponseDto
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface StoryRepository {
    suspend fun getStories(page: Int, perPage: Int): NetworkResult<StoriesResponseDto>
    suspend fun getStoriesByCategory(slug: String, page: Int = 1, perPage: Int = 15): NetworkResult<CategoryStoriesResponseDto>
}
