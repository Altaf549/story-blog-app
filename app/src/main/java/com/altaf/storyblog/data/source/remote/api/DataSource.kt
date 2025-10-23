package com.altaf.storyblog.data.source.remote.api

import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.CategoryStoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.data.source.remote.dto.StoriesResponseDto
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface DataSource {
    suspend fun getHomeData(): NetworkResult<HomeResponseDto>
    suspend fun getCategories(): NetworkResult<CategoriesResponseDto>
    suspend fun getStories(page: Int = 1, perPage: Int = 15): NetworkResult<StoriesResponseDto>
    suspend fun getStoriesByCategory(slug: String, page: Int = 1, perPage: Int = 15): NetworkResult<CategoryStoriesResponseDto>
}