package com.altaf.storyblog.domain.usecase.story

import android.util.Log
import com.altaf.storyblog.domain.mapper.StoryMapper
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.StoryRepository
import javax.inject.Inject

class GetStoriesByCategoryUseCaseImpl @Inject constructor(
    private val repository: StoryRepository,
    private val mapper: StoryMapper
) : GetStoriesByCategoryUseCase {

    override suspend fun invoke(
        categorySlug: String,
        page: Int,
        perPage: Int
    ): NetworkResult<MutableList<Story>> {
        return try {
            Log.d("TAG", "invoke: $categorySlug")
            val result = repository.getStoriesByCategory(categorySlug, page, perPage)
            if (result.data != null) {
                val storyDtos = result.data.stories.data
                val stories = mapper.mapToDomainList(storyDtos)
                NetworkResult.Success(stories, result.message ?: "Categories fetched successfully")
            } else {
                NetworkResult.Error(result.message ?: "Failed to fetch categories")
            }
        } catch (e: Exception) {
            NetworkResult.Error("An error occurred: ${e.message}")
        }
    }
}
