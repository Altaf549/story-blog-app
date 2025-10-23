package com.altaf.storyblog.data.source.remote.dto

import com.google.gson.annotations.SerializedName

data class StoriesResponseDto(
    @SerializedName("data")
    val data: MutableList<StoryDto>,
    
    @SerializedName("current_page")
    val currentPage: Int,
    
    @SerializedName("last_page")
    val lastPage: Int,
    
    @SerializedName("per_page")
    val perPage: Int,
    
    @SerializedName("total")
    val total: Int
)
