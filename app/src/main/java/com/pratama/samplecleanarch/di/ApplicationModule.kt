package com.pratama.samplecleanarch.di

import com.pratama.samplecleanarch.BuildConfig
import com.pratama.samplecleanarch.data.interceptor.HeaderInterceptor
import com.pratama.samplecleanarch.data.service.NewsApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ActivityComponent::class)
class ApplicationModule {

    @Provides
    fun provideOkHttpClient(headerInterceptor: HeaderInterceptor) = if (BuildConfig.DEBUG) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(headerInterceptor)
            .build()
    } else {
        OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()
    }


    @Provides
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttpClient)
        .build()

    @Provides
    fun provideNewsApiServices(retrofit: Retrofit): NewsApiService =
        retrofit.create(NewsApiService::class.java)

}