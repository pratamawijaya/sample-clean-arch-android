package com.pratama.samplecleanarch.domain.repository

import com.pratama.core_android.Either
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.domain.entity.Article

interface ArticleRepository {
    suspend fun getTopHeadlines(
        country: String,
        category: String,
        page: Int
    ): Either<Failure, List<Article>>
}