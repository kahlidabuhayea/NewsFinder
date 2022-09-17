package com.app.newsfinder.data.client

import com.app.newsfinder.BuildConfig
import com.app.newsfinder.di.NewsApi
import retrofit2.Retrofit

class NewsAPIClient (@NewsApi retrofitBuilder: Retrofit.Builder): NetworkClient(retrofitBuilder) {

    override val baseUrl = BuildConfig.NEWSAPI_ENDPOINT

}