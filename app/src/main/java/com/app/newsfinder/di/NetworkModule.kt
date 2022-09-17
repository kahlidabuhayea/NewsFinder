package com.app.newsfinder.di

import com.app.newsfinder.BuildConfig
import com.app.newsfinder.data.interceptor.CurlLoggerInterceptor
import com.app.newsfinder.data.interceptor.NewsApiInterceptor
import com.app.newsfinder.data.interceptor.NewsDataInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @NewsApi
    @Singleton
    @Provides
    fun provideNewsApiOkHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(NewsApiInterceptor())
            .addInterceptor(CurlLoggerInterceptor())
            .connectTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }

    @NewsApi
    @Singleton
    @Provides
    fun provideNewsApiRetrofitBuilder(
        @NewsApi okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    }


    @NewsData
    @Singleton
    @Provides
    fun provideNewsDataOkHttpClient(
    ): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .addInterceptor(NewsDataInterceptor())
            .addInterceptor(CurlLoggerInterceptor())
            .connectTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(BuildConfig.CONNECTION_TIMEOUT, TimeUnit.SECONDS)
        return builder.build()
    }

    @NewsData
    @Singleton
    @Provides
    fun provideNewsDataRetrofitBuilder(
        @NewsData okHttpClient: OkHttpClient
    ): Retrofit.Builder {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return Retrofit.Builder()
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create(moshi).asLenient())
    }



}

@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsData


@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.FUNCTION, AnnotationTarget.TYPE)
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class NewsApi
