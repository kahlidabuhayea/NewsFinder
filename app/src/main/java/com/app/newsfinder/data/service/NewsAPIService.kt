package com.app.newsfinder.data.service

import com.app.newsfinder.data.model.GetNewsFromNewApiResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPIService {

    @GET("v2/top-headlines/")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("country") country: String?,
        @Query("category") category: String?
    ):GetNewsFromNewApiResponse
}