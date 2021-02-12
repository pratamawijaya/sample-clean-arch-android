package com.pratama.samplecleanarch.data.repository

import com.pratama.core_android.Either
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.data.datasource.remote.ArticleRemoteDatasource
import com.pratama.samplecleanarch.data.mapper.ArticleMapper
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.repository.ArticleRepository

class ArticleRepositoryImpl constructor(
    private val remoteDatasource: ArticleRemoteDatasource,
    private val articleMapper: ArticleMapper
) :
    ArticleRepository {
    override suspend fun getTopHeadlines(
        country: String,
        category: String,
        page: Int
    ): Either<Failure, List<Article>> {
        return try {
            val result = remoteDatasource.getTopHeadlines(country, category)
            val listNews = articleMapper.mapToListDomain(result.articles)
            Either.Right(listNews)
        } catch (e: Exception) {
            Either.Left(Failure.ServerError)
        }
    }
}