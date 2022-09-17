package com.app.newsfinder.data.client
import com.app.newsfinder.BuildConfig
import com.app.newsfinder.di.NewsData
import retrofit2.Retrofit

class NewsDataNetworkClient (@NewsData retrofitBuilder: Retrofit.Builder): NetworkClient(retrofitBuilder) {

    override val baseUrl = BuildConfig.NEWSDATA_ENDPOINT

}