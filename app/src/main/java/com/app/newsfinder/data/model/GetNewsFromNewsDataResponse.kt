package com.app.newsfinder.data.model

import com.squareup.moshi.Json

data class GetNewsFromNewsDataResponse(@Json(name = "results") val results: List<NewsDataModel>)
