package com.app.newsfinder.data.model

import com.squareup.moshi.Json

data class GetNewsFromNewApiResponse(@Json(name = "articles") val articles: List<NewsApiModel>)
