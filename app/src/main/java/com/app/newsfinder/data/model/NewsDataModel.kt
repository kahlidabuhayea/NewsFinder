package com.app.newsfinder.data.model

import com.squareup.moshi.Json

data class NewsDataModel(
    @Json(name = "title") val title: String?,
    @Json(name = "description") val description: String?,
    @Json(name = "pubDate") val pubDate: String?,
    @Json(name = "image_url") val image_url: String?,
    @Json(name = "creator") val creator: List<String>?,
    @Json(name = "link") val link: String?
)
