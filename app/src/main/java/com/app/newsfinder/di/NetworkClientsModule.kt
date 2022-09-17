package com.app.newsfinder.di
import com.app.newsfinder.data.client.NewsAPIClient
import com.app.newsfinder.data.client.NewsDataNetworkClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkClientsModule {

    @Provides
    @Singleton
    fun provideNewsApiNetworkClient(@NewsApi retrofitBuilder:  Retrofit.Builder): NewsAPIClient {
        return NewsAPIClient(retrofitBuilder)
    }

    @Provides
    @Singleton
    fun provideNewsDataNetworkClient(@NewsData retrofitBuilder: Retrofit.Builder): NewsDataNetworkClient {
        return NewsDataNetworkClient(retrofitBuilder)
    }

}