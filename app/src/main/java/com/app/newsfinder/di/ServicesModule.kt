package com.app.newsfinder.di
import com.app.newsfinder.data.client.NewsAPIClient
import com.app.newsfinder.data.client.NewsDataNetworkClient
import com.app.newsfinder.data.service.NewsAPIService
import com.app.newsfinder.data.service.NewsDataService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ServicesModule {

    @Provides
    @Singleton
    fun provideNewsAPIService(newsAPIClient: NewsAPIClient): NewsAPIService {
        return newsAPIClient.build().create(NewsAPIService::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDataService(newsDataNetworkClient: NewsDataNetworkClient): NewsDataService {
        return newsDataNetworkClient.build().create(NewsDataService::class.java)
    }

}