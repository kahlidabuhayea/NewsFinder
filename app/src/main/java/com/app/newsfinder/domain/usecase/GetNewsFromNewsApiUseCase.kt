package com.app.newsfinder.domain.usecase

import com.app.newsfinder.data.model.NewsApiModel
import com.app.newsfinder.domain.repository.NewsAPIRepository
import javax.inject.Inject

class GetNewsFromNewsApiUseCase @Inject constructor(private val newsAPIRepository: NewsAPIRepository) :
    BaseUseCase<List<NewsApiModel>, GetNewsFromNewsApiUseCase.Params>() {


    data class Params(val query: String, val country: String ?, val category: String ?)

    override suspend fun execute(params: Params): List<NewsApiModel> =
        newsAPIRepository.getNews(params)
}