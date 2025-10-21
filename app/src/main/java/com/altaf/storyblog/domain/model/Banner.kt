package com.altaf.storyblog.domain.model

data class Banner(
    val id: Long,
    val title: String,
    val imageUrl: String,
    val linkUrl: String,
    val position: Int
)
