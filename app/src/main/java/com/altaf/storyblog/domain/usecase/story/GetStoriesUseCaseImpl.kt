package com.altaf.storyblog.domain.usecase.story

import com.altaf.storyblog.domain.mapper.StoryMapper
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.StoryRepository
import javax.inject.Inject

class GetStoriesUseCaseImpl @Inject constructor(
    private val repository: StoryRepository,
    private val mapper: StoryMapper
) : GetStoriesUseCase {

    override suspend fun invoke(page: Int, perPage: Int): NetworkResult<MutableList<Story>> {
        return try {
            val result = repository.getStories(page, perPage)
            if (result.data != null) {
                val stories = mapper.mapToDomainList(result.data.data)
                NetworkResult.Success(stories, result.message ?: "Categories fetched successfully")
            } else {
                NetworkResult.Error(result.message ?: "Failed to fetch categories")
            }
        } catch (e: Exception) {
            NetworkResult.Error("An error occurred: ${e.message}")
        }
    }
}
