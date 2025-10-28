package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.BuildConfig
import com.altaf.storyblog.domain.model.Story
import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class StoryDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("content")
    val content: String,
    
    @SerializedName("image_id")
    val imageId: String,
    
    @SerializedName("youtube_url")
    val youtubeUrl: String,
    
    @SerializedName("user")
    val user: UserDto,
    
    @SerializedName("category")
    val category: CategoryDto,
    
    @SerializedName("created_at")
    val createdAt: String
) {
    fun toStory(): Story {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        dateFormat.timeZone = TimeZone.getTimeZone("UTC")
        
        return Story(
            id = id,
            title = title,
            content = content,
            bannerImageUrl = BuildConfig.IMAGE_URL + imageId,
            youtubeUrl = youtubeUrl,
            user = user.toUser(),
            category = category.toCategory(),
            createdAt = dateFormat.parse(createdAt) ?: Date()
        )
    }
}
