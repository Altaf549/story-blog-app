package com.altaf.storyblog.data.repository

import com.altaf.storyblog.data.source.remote.api.DataSource
import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.CategoryRepository
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val dataSource: DataSource
) : CategoryRepository {
    override suspend fun getCategories() = dataSource.getCategories()
}
