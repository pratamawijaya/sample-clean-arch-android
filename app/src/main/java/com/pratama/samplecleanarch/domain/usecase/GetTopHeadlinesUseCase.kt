package com.pratama.samplecleanarch.domain.usecase

import com.pratama.core_android.Either
import com.pratama.core_android.base.UseCase
import com.pratama.core_android.exceptions.Failure
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.repository.ArticleRepository
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(private val repository: ArticleRepository) :
    UseCase<List<Article>, GetTopHeadlinesUseCase.Params>() {

    data class Params(val country: String, val category: String)

    override suspend fun run(params: Params): Either<Failure, List<Article>> {
        return repository.getTopHeadlines(params.country, params.category)
    }
}