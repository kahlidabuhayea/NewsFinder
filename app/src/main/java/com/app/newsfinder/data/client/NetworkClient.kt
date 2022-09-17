package com.app.newsfinder.data.client

import com.app.newsfinder.di.NewsApi
import retrofit2.Retrofit

abstract class NetworkClient(@NewsApi private val retrofitBuilder: Retrofit.Builder){

    protected abstract val baseUrl: String

    fun build(): Retrofit {
        return retrofitBuilder.baseUrl(baseUrl).build()
    }
}