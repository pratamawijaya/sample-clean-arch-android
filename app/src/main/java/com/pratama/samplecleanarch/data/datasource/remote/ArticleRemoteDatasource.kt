package com.pratama.samplecleanarch.data.datasource.remote

import com.pratama.samplecleanarch.data.model.TopHeadlinesResponse
import com.pratama.samplecleanarch.data.services.NewsApiService
import javax.inject.Inject

interface ArticleRemoteDatasource {
    suspend fun getTopHeadlines(category: String, country: String): TopHeadlinesResponse
}

class ArticleRemoteDatasourceImpl @Inject constructor(val services: NewsApiService) :
    ArticleRemoteDatasource {

    override suspend fun getTopHeadlines(category: String, country: String): TopHeadlinesResponse {
        return services.getTopHeadlines(country, category)
    }

}