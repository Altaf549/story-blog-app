package com.altaf.storyblog.domain.mapper

import com.altaf.storyblog.data.source.remote.dto.CategoryDto
import com.altaf.storyblog.data.source.remote.dto.StoryDto
import com.altaf.storyblog.data.source.remote.dto.StoriesResponseDto
import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.model.networkModel.NetworkResult.Error
import com.altaf.storyblog.domain.model.networkModel.NetworkResult.Success
import javax.inject.Inject
import javax.inject.Singleton

interface StoryMapper {
    fun mapToDomainList(dtos: MutableList<StoryDto>): MutableList<Story>
}

class StoryMapperImpl @Inject constructor() : StoryMapper {
    override fun mapToDomainList(dtos: MutableList<StoryDto>): MutableList<Story> {
        return dtos.map { it.toStory() } as MutableList
    }
}
