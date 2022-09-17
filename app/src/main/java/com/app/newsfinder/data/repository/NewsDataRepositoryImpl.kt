package com.app.newsfinder.data.repository

import com.app.newsfinder.data.model.NewsDataModel
import com.app.newsfinder.data.service.NewsAPIService
import com.app.newsfinder.data.service.NewsDataService
import com.app.newsfinder.domain.repository.NewsDataRepository
import com.app.newsfinder.domain.usecase.GetNewsFromNewsDataUseCase
import javax.inject.Inject

class NewsDataRepositoryImpl @Inject constructor(
    private val newsDataService: NewsDataService
) : NewsDataRepository {

    override suspend fun getNews(params: GetNewsFromNewsDataUseCase.Params): List<NewsDataModel> =
        newsDataService.getNews(params.query, params.country, params.category).results

}