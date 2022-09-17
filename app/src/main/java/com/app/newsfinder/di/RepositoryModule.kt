package com.app.newsfinder.di

import com.app.newsfinder.data.repository.NewsAPIRepositoryImpl
import com.app.newsfinder.data.repository.NewsDataRepositoryImpl
import com.app.newsfinder.data.service.NewsAPIService
import com.app.newsfinder.data.service.NewsDataService
import com.app.newsfinder.domain.repository.NewsAPIRepository
import com.app.newsfinder.domain.repository.NewsDataRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesNewsDataRepository(newsData: NewsDataService): NewsDataRepository {
        return NewsDataRepositoryImpl(newsData)
    }

    @Provides
    @Singleton
    fun providesNewsAPIRepository(newsAPI: NewsAPIService): NewsAPIRepository {
        return NewsAPIRepositoryImpl(newsAPI)
    }

}