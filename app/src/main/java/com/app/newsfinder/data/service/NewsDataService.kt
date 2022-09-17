package com.app.newsfinder.data.service

import com.app.newsfinder.data.model.GetNewsFromNewsDataResponse

import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataService {

    @GET("api/1/news")
    suspend fun getNews(
        @Query("q") query: String,
        @Query("country") country: String?,
        @Query("category") category: String?
    ): GetNewsFromNewsDataResponse

}