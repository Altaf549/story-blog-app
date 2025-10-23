package com.altaf.storyblog.domain.usecase.category

import com.altaf.storyblog.domain.model.Category
import com.altaf.storyblog.domain.model.networkModel.NetworkResult

interface GetCategoriesUseCase {
    suspend operator fun invoke(): NetworkResult<MutableList<Category>>
}
