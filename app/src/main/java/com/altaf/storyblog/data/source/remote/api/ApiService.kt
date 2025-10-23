package com.altaf.storyblog.data.source.remote.api

import com.altaf.storyblog.data.source.remote.dto.CategoriesResponseDto
import com.altaf.storyblog.data.source.remote.dto.HomeResponseDto
import retrofit2.http.GET

interface ApiService {
    @GET("home")
    suspend fun getHomeData(): HomeResponseDto
    
    @GET("categories")
    suspend fun getCategories(): CategoriesResponseDto
}