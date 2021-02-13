package com.pratama.samplecleanarch.data.services

import com.pratama.samplecleanarch.data.model.TopHeadlinesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category: String
    ): TopHeadlinesResponse
}