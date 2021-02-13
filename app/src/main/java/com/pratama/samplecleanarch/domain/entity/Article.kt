package com.pratama.samplecleanarch.domain.entity

data class Article(
    val source: Source,
    val author: String,
    val title: String,
    val description: String,
    val thumbnail: String
)
