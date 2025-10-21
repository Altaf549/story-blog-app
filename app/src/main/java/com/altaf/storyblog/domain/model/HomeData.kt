package com.altaf.storyblog.domain.model

data class HomeData(
    val banners: MutableList<Banner> = mutableListOf(),
    val categories: MutableList<Category> = mutableListOf(),
    val stories: MutableList<Story> = mutableListOf()
)
