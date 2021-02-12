package com.pratama.samplecleanarch.data.model

data class GetTopHeadlineResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<ArticleModel>
)