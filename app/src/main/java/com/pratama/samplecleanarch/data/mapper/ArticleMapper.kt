package com.pratama.samplecleanarch.data.mapper

import com.pratama.core_android.base.BaseMapper
import com.pratama.samplecleanarch.data.model.ArticleModel
import com.pratama.samplecleanarch.data.model.SourceModel
import com.pratama.samplecleanarch.domain.entity.Article
import com.pratama.samplecleanarch.domain.entity.Source

class ArticleMapper : BaseMapper<ArticleModel, Article> {

    override fun mapToDomain(model: ArticleModel): Article {
        return with(model) {
            Article(
                source = Source("", ""),
                title = title ?: "",
                author = author ?: "",
                description = description ?: "",
                thumbnail = urlToImage ?: ""
            )
        }
    }

    override fun mapToModel(domain: Article): ArticleModel {
        return with(domain) {
            ArticleModel(source = SourceModel(id = "", name = ""))
        }
    }

    fun mapToListDomain(listModel: List<ArticleModel>): List<Article> {
        val listDomain = mutableListOf<Article>()
        listModel.map { listDomain.add(mapToDomain(it)) }
        return listDomain
    }
}