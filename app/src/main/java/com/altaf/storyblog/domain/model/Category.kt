package com.altaf.storyblog.domain.model

data class Category(
    val id: Long = -1,
    val name: String = "",
    val slug: String = "",
    val description: String = "",
    val imageUrl: String = ""
)
