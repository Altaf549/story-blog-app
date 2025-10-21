package com.altaf.storyblog.domain.model

import java.util.Date

data class Story(
    val id: Long,
    val title: String,
    val content: String,
    val bannerImageUrl: String,
    val user: User,
    val category: Category,
    val createdAt: Date
)
