package com.app.newsfinder.domain.usecase

import com.app.newsfinder.data.model.NewsDataModel
import com.app.newsfinder.domain.repository.NewsAPIRepository
import com.app.newsfinder.domain.repository.NewsDataRepository
import javax.inject.Inject

class GetNewsFromNewsDataUseCase @Inject constructor(private val newsDataRepository: NewsDataRepository) :
    BaseUseCase<List<NewsDataModel>, GetNewsFromNewsDataUseCase.Params>() {


    data class Params(val query: String, val country: String ?, val category: String?)

    override suspend fun execute(params: Params): List<NewsDataModel> =
        newsDataRepository.getNews(params)
}