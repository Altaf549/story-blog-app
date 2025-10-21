package com.altaf.storyblog.data.source.remote.api

import retrofit2.http.GET

interface ApiService {
    @GET("/api/home")
    suspend fun home(): Unit
}