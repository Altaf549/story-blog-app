package com.altaf.storyblog.domain.mapper

import com.altaf.storyblog.data.source.remote.dto.CategoryDto
import com.altaf.storyblog.domain.model.Category
import javax.inject.Inject

interface CategoryMapper {
    fun mapToDomainList(dtos: MutableList<CategoryDto>): MutableList<Category>
}

class CategoryMapperImpl @Inject constructor() : CategoryMapper {
    override fun mapToDomainList(dtos: MutableList<CategoryDto>): MutableList<Category> {
        return dtos.map { it.toCategory() } as MutableList
    }
}
