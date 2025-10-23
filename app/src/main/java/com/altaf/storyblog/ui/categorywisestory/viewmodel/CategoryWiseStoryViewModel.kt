package com.altaf.storyblog.ui.categorywisestory.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.altaf.storyblog.common.base.BaseViewModel
import com.altaf.storyblog.data.paging.CategoryWiseStoryPagingSource
import com.altaf.storyblog.data.paging.StoryPagingSource
import com.altaf.storyblog.domain.model.Story
import com.altaf.storyblog.domain.usecase.story.GetStoriesByCategoryUseCase
import com.altaf.storyblog.ui.story.viewmodel.StoryEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

private const val PAGE_SIZE = 15
@HiltViewModel
class CategoryWiseStoryViewModel @Inject constructor(
    private val getStoriesByCategoryUseCase: GetStoriesByCategoryUseCase
) : BaseViewModel<CategoryWiseStoryState, CategoryWiseStoryEvent>() {
    private var currentCategorySlug: String = ""
    fun setStoriesByCategory(slug: String) {
        currentCategorySlug = slug
    }
    val storiesFlow: Flow<PagingData<Story>> = Pager(
        config = PagingConfig(
            pageSize = PAGE_SIZE,
            enablePlaceholders = false,
            initialLoadSize = PAGE_SIZE
        ),
        pagingSourceFactory = {
            CategoryWiseStoryPagingSource(
                getStoriesByCategoryUseCase = getStoriesByCategoryUseCase,
                categorySlug = currentCategorySlug
            )
        }
    ).flow.cachedIn(viewModelScope)

    private val _uiEvent = MutableStateFlow<StoryEvent>(StoryEvent.Empty)
    val uiEvent: StateFlow<StoryEvent> = _uiEvent.asStateFlow()

    fun onStoryClicked() {
        _uiEvent.value = StoryEvent.NavigateToSingleStory
    }

    fun clearEvent() {
        _uiEvent.value = StoryEvent.Empty
    }
}
