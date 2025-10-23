package com.altaf.storyblog.domain.repository

import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface CategoryRepository {
    suspend fun getCategories(): NetworkResult<CategoriesResponseDto>
}
