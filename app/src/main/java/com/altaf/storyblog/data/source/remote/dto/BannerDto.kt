package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.domain.model.Banner
import com.google.gson.annotations.SerializedName

data class BannerDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("image_path")
    val imagePath: String,
    
    @SerializedName("image_url")
    val imageUrl: String,
    
    @SerializedName("link_url")
    val linkUrl: String,
    
    @SerializedName("position")
    val position: Int
) {
    fun toBanner(): Banner {
        return Banner(
            id = id,
            title = title,
            imageUrl = imageUrl,
            linkUrl = linkUrl,
            position = position
        )
    }
}
