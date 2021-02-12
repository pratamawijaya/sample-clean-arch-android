package com.pratama.samplecleanarch.data.mapper

import com.pratama.core_android.base.BaseMapper
import com.pratama.samplecleanarch.data.model.ArticleModel
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.entity.Source

class ArticleMapper : BaseMapper<ArticleModel, Article> {
    override fun mapToDomain(model: ArticleModel): Article {
        return with(model) {
            Article(
                author = author ?: "",
                title = title ?: "",
                description = description ?: "",
                url = "",
                thumbnail = "",
                publishedAt = "",
                content = "",
                source = Source(id = 1, name = "")
            )
        }
    }

    override fun mapToModel(domain: Article): ArticleModel {
        return ArticleModel()
    }

    fun mapToListDomain(list: List<ArticleModel>): List<Article> {
        val listDomain = mutableListOf<Article>()
        list.map { listDomain.add(mapToDomain(it)) }
        return listDomain
    }
}