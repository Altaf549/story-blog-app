package com.altaf.storyblog.data.source.remote.dto

import com.altaf.storyblog.domain.model.Category
import com.google.gson.annotations.SerializedName

data class CategoriesResponseDto(
    @SerializedName("data")
    val data: MutableList<CategoryDto>
) {
    fun toCategories(): MutableList<Category> = data.map { it.toCategory() } as MutableList<Category>
}
