package com.pratama.samplecleanarch.data.model

data class TopHeadlinesResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)