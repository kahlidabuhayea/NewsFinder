package com.app.newsfinder.domain.repository

import com.app.newsfinder.data.model.NewsApiModel
import com.app.newsfinder.domain.usecase.GetNewsFromNewsApiUseCase
import retrofit2.http.Query

interface NewsAPIRepository {

    suspend fun getNews(params: GetNewsFromNewsApiUseCase.Params): List<NewsApiModel>
}