package com.app.newsfinder.data.repository

import com.app.newsfinder.data.model.NewsApiModel
import com.app.newsfinder.data.service.NewsAPIService
import com.app.newsfinder.domain.repository.NewsAPIRepository
import com.app.newsfinder.domain.usecase.GetNewsFromNewsApiUseCase
import javax.inject.Inject

class NewsAPIRepositoryImpl @Inject constructor(
    private val newsAPIService: NewsAPIService
) : NewsAPIRepository {

    override suspend fun getNews(params: GetNewsFromNewsApiUseCase.Params): List<NewsApiModel> =
        newsAPIService.getNews(params.query, params.country, params.category).articles


}