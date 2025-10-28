package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.BuildConfig
import com.altaf.storyblog.domain.model.Banner
import com.google.gson.annotations.SerializedName

data class BannerDto(
    @SerializedName("id")
    val id: Long,
    
    @SerializedName("title")
    val title: String,
    
    @SerializedName("image_id")
    val imageId: String,
    
    @SerializedName("link_url")
    val linkUrl: String,
    
    @SerializedName("position")
    val position: Int
) {
    fun toBanner(): Banner {
        return Banner(
            id = id,
            title = title,
            imageUrl = BuildConfig.IMAGE_URL + imageId,
            linkUrl = linkUrl,
            position = position
        )
    }
}
