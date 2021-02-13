package com.pratama.samplecleanarch.data.datasource.local

import com.github.ajalt.timberkt.d
import com.pratama.samplecleanarch.data.model.ArticleModel

interface ArticleLocalDatasource {
    suspend fun getTopHeadlines(): List<ArticleModel>
    fun setTopHeadlines(articleModels: List<ArticleModel>)
}

class ArticleLocalDataSourceImpl : ArticleLocalDatasource {
    var list = mutableListOf<ArticleModel>()

    override suspend fun getTopHeadlines(): List<ArticleModel> {
        return list
    }

    override fun setTopHeadlines(articleModels: List<ArticleModel>) {
        d { "set data topheadlines ${articleModels.size}" }
        if (list.isNotEmpty()) list.clear()
        list.addAll(articleModels)
    }
}