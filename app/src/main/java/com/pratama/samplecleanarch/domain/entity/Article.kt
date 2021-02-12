package com.pratama.samplecleanarch.domain.entity

data class Article(
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val thumbnail: String,
    val publishedAt: String,
    val content: String,
    val source: Source
)