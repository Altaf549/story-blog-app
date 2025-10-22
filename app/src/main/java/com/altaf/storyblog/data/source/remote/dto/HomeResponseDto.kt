package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.domain.model.Banner
import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.Story
import com.google.gson.annotations.SerializedName

data class HomeResponseDto(
    @SerializedName("banners")
    val banners: MutableList<BannerDto>,
    
    @SerializedName("categories")
    val categories: MutableList<CategoryDto>,
    
    @SerializedName("stories")
    val stories: List<StoryDto>
) {
    fun toBanners(): List<Banner> = banners.map { it.toBanner() }
    
    fun toCategories(): List<Category> = categories.map { it.toCategory() }
    
    fun toStories(): List<Story> = stories.map { it.toStory() }
}
