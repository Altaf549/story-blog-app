package com.altaf.storyblog.domain.mapper

import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.Banner
import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.HomeData
import com.altaf.storyblog.domain.model.Story
import javax.inject.Inject
import javax.inject.Singleton

interface HomeDataMapper {
    fun mapToDomain(dto: HomeResponseDto): HomeData
}

@Singleton
class HomeDataMapperImpl @Inject constructor() : HomeDataMapper {
    override fun mapToDomain(dto: HomeResponseDto): HomeData {
        return HomeData(
            banners = dto.toBanners() as MutableList<Banner>,
            categories = dto.toCategories() as MutableList<Category>,
            stories = dto.toStories() as MutableList<Story>
        )
    }
}
