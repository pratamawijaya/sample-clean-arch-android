package com.pratama.samplecleanarch.data.datasource.remote

import com.pratama.samplecleanarch.data.model.GetTopHeadlineResponse
import com.pratama.samplecleanarch.data.service.NewsApiService

interface ArticleRemoteDatasource {
    suspend fun getTopHeadlines(country: String, category: String): GetTopHeadlineResponse
}


class ArticleRemoteDatasourceImpl constructor(private val service: NewsApiService) :
    ArticleRemoteDatasource {

    override suspend fun getTopHeadlines(
        country: String,
        category: String
    ): GetTopHeadlineResponse {
        return service.getTopHeadlines(country, category)
    }

}