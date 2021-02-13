package com.pratama.samplecleanarch.di

import com.pratama.samplecleanarch.data.datasource.local.ArticleLocalDataSourceImpl
import com.pratama.samplecleanarch.data.datasource.local.ArticleLocalDatasource
import com.pratama.samplecleanarch.data.datasource.remote.ArticleRemoteDatasource
import com.pratama.samplecleanarch.data.datasource.remote.ArticleRemoteDatasourceImpl
import com.pratama.samplecleanarch.data.mapper.ArticleMapper
import com.pratama.samplecleanarch.data.repository.ArticleRepositoryImpl
import com.pratama.samplecleanarch.data.services.NewsApiService
import com.pratama.samplecleanarch.domain.repository.ArticleRepository
import com.pratama.samplecleanarch.domain.usecase.GetTopHeadlinesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class ActivityModule {

    @Provides
    @ActivityScoped
    fun provideLocalDatasource(): ArticleLocalDatasource = ArticleLocalDataSourceImpl()

    @Provides
    @ActivityScoped
    fun provideRemoteDatasource(newsApiService: NewsApiService): ArticleRemoteDatasource =
        ArticleRemoteDatasourceImpl(newsApiService)

    @Provides
    @ActivityScoped
    fun provideArticleMappr() = ArticleMapper()

    @Provides
    @ActivityScoped
    fun provideArticleRepository(
        remoteDatasource: ArticleRemoteDatasource,
        localDatasource: ArticleLocalDatasource,
        articleMapper: ArticleMapper
    ): ArticleRepository = ArticleRepositoryImpl(remoteDatasource, localDatasource, articleMapper)

    @Provides
    @ActivityScoped
    fun provideGetTopHeadlineUseCase(repository: ArticleRepository) =
        GetTopHeadlinesUseCase(repository)
}