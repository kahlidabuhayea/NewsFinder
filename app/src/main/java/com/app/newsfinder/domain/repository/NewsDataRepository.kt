package com.app.newsfinder.domain.repository

import com.app.newsfinder.data.model.NewsApiModel
import com.app.newsfinder.data.model.NewsDataModel
import com.app.newsfinder.domain.usecase.GetNewsFromNewsDataUseCase
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsDataRepository {

    suspend fun getNews(params: GetNewsFromNewsDataUseCase.Params): List<NewsDataModel>

}