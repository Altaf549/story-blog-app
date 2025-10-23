package com.altaf.storyblog.domain.usecase.category

import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.domain.mapper.CategoryMapper
import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.repository.CategoryRepository
import javax.inject.Inject

class GetCategoriesUseCaseImpl @Inject constructor(
    private val repository: CategoryRepository,
    private val mapper: CategoryMapper
) : GetCategoriesUseCase {

    override suspend fun invoke(): NetworkResult<MutableList<Category>> {
        return try {
            val result = repository.getCategories()
            if (result.data != null) {
                val categories = mapper.mapToDomainList(result.data.data)
                NetworkResult.Success(categories, result.message ?: "Categories fetched successfully")
            } else {
                NetworkResult.Error(result.message ?: "Failed to fetch categories")
            }
        } catch (e: Exception) {
            NetworkResult.Error("An error occurred: ${e.message}")
        }
    }
}
