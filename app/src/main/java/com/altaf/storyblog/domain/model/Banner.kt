package com.altaf.storyblog.domain.model

data class Banner(
    val id: Long = -1,
    val title: String = "",
    val imageUrl: String = "",
    val linkUrl: String = "",
    val position: Int = -1
)
