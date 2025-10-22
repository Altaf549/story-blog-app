package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoryDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("name")
    val name: String,
    
    @SerializedName("slug")
    val slug: String,
    
    @SerializedName("description")
    val description: String?,
    
    @SerializedName("image")
    val image: String?,
    
    @SerializedName("image_url")
    val imageUrl: String?
) {
    fun toCategory(): Category {
        return Category(
            id = id,
            name = name,
            slug = slug,
            description = description ?: "",
            imageUrl = imageUrl ?: ""
        )
    }
}
