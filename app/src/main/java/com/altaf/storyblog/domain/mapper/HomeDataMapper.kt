package com.altaf.storyblog.domain.mapper

import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.domain.model.HomeData
import javax.inject.Inject

interface HomeDataMapper {
    fun mapToDomain(dto: HomeResponseDto): HomeData {
        return HomeData(
            banners = dto.toBanners(),
            categories = dto.toCategories(),
            stories = dto.toStories()
        )
    }
}
