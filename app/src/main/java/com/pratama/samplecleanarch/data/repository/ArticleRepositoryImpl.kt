package com.pratama.samplecleanarch.data.repository

import com.github.ajalt.timberkt.d
import com.pratama.core_android.Either
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.data.datasource.local.ArticleLocalDatasource
import com.pratama.samplecleanarch.data.datasource.remote.ArticleRemoteDatasource
import com.pratama.samplecleanarch.data.mapper.ArticleMapper
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.repository.ArticleRepository
import java.lang.Exception

class ArticleRepositoryImpl constructor(
    private val remoteDatasource: ArticleRemoteDatasource,
    private val localDatasource: ArticleLocalDatasource,
    private val articleMapper: ArticleMapper
) :
    ArticleRepository {

    override suspend fun getTopHeadlines(
        country: String,
        category: String
    ): Either<Failure, List<Article>> {
        if (localDatasource.getTopHeadlines().isNotEmpty()) {
            d { "get data from local" }
            return Either.Right(articleMapper.mapToListDomain(localDatasource.getTopHeadlines()))
        } else {
            d { "get data from remote" }
            return try {
                val result = remoteDatasource.getTopHeadlines(category, country)

                // set to local storage
                localDatasource.setTopHeadlines(result.articles)

                Either.Right(articleMapper.mapToListDomain(result.articles))
            } catch (e: Exception) {
                Either.Left(Failure.ServerError("error ${e.localizedMessage}"))
            }
        }
    }
}