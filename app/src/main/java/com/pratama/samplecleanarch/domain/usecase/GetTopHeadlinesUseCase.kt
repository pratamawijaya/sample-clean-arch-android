package com.pratama.samplecleanarch.domain.usecase

import com.pratama.core_android.Either
import com.pratama.core_android.base.UseCase
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.repository.ArticleRepository

class GetTopHeadlinesUseCase(private val repository: ArticleRepository) :
    UseCase<Either<Failure, List<Article>>, GetTopHeadlinesUseCase.Param>() {

    data class Param(
        val category: String,
        val country: String,
        val page: Int
    )

    override suspend fun run(params: Param): Either<Failure, List<Article>> {
        return with(params) {
            repository.getTopHeadlines(country, category, page)
        }
    }
}