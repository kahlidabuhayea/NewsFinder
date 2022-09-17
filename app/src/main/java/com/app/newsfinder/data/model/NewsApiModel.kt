package com.app.newsfinder.data.model

import com.squareup.moshi.Json

data class NewsApiModel(
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "publishedAt") val publishedAt: String?,
    @Json(name = "urlToImage") val urlToImage: String?,
    @Json(name = "author") val author: String?,
    @Json(name = "url") val url: String?

)
