package com.altaf.storyblog.data.source.remote.api

import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.CategoryStoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import com.altaf.storyblog.data.source.remote.dto.StoriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("home")
    suspend fun getHomeData(): HomeResponseDto
    
    @GET("categories")
    suspend fun getCategories(): CategoriesResponseDto
    
    @GET("stories")
    suspend fun getStories(
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15
    ): StoriesResponseDto
    
    @GET("categories/{slug}/stories")
    suspend fun getStoriesByCategory(
        @Path("slug") slug: String,
        @Query("page") page: Int = 1,
        @Query("per_page") perPage: Int = 15
    ): CategoryStoriesResponseDto
}