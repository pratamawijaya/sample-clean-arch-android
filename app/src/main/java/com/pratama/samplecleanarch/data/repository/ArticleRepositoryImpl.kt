package com.pratama.samplecleanarch.data.repository

import com.pratama.core_android.Either
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.repository.ArticleRepository

class ArticleRepositoryImpl : ArticleRepository {
    override suspend fun getTopHeadlines(
        country: String,
        category: String,
        page: Int
    ): Either<Failure, List<Article>> {
        // todo : setup data source
    }
}