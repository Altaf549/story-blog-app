package com.altaf.storyblog.domain.model

import java.util.Date

data class Story(
    val id: Long = -1,
    val title: String = "",
    val content: String = "",
    val bannerImageUrl: String = "",
    val user: User = User(),
    val category: Category = Category(),
    val createdAt: Date = Date()
)
