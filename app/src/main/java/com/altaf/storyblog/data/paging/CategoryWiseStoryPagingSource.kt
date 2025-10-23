package com.altaf.storyblog.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.model.networkModel.NetworkResult
import com.altaf.storyblog.domain.usecase.story.GetStoriesByCategoryUseCase
import java.io.IOException

class CategoryWiseStoryPagingSource(
    private val getStoriesByCategoryUseCase: GetStoriesByCategoryUseCase,
    private val categorySlug: String
) : PagingSource<Int, Story>() {

    override fun getRefreshKey(state: PagingState<Int, Story>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Story> {
        return try {
            val page = params.key ?: 1
            val pageSize = params.loadSize

            when (val response = getStoriesByCategoryUseCase(categorySlug = categorySlug, page = page, perPage = pageSize)) {
                is NetworkResult.Success -> {
                    val stories = response.data ?: emptyList()
                    val nextKey = if (stories.size < pageSize) null else page + 1
                    val prevKey = if (page == 1) null else page - 1

                    LoadResult.Page(
                        data = stories,
                        prevKey = prevKey,
                        nextKey = nextKey
                    )
                }
                is NetworkResult.Error -> {
                    LoadResult.Error(IOException(response.message ?: "Unknown error"))
                }
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}
