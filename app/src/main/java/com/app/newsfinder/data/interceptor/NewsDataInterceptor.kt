package com.app.newsfinder.data.interceptor

import com.app.newsfinder.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response


class NewsDataInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val originalHttpUrl = original.url

        val url = originalHttpUrl.newBuilder()
            .addQueryParameter("apikey", BuildConfig.NEWSDATA_API_KEY)
            .build()

        val requestBuilder = original.newBuilder()
            .url(url)

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}