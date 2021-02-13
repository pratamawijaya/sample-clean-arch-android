package com.pratama.samplecleanarch.data.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.pratama.samplecleanarch.data.datasource.local.ArticleLocalDatasource
import com.pratama.samplecleanarch.data.datasource.remote.ArticleRemoteDatasource
import com.pratama.samplecleanarch.data.mapper.ArticleMapper
import com.pratama.samplecleanarch.domain.repository.ArticleRepository
import io.mockk.impl.annotations.MockK
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ArticleRepositoryImplTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    lateinit var articleRepository: ArticleRepository

    @MockK
    lateinit var remoteDatasource: ArticleRemoteDatasource

    @MockK
    lateinit var localDatasource: ArticleLocalDatasource

    private var articleMapper = ArticleMapper()

    @Before
    fun setUp() {
        articleRepository = ArticleRepositoryImpl(remoteDatasource, localDatasource, articleMapper)
    }


}