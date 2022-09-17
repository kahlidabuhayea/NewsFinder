package com.app.newsfinder.presentation.news.viewmodel

import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.newsfinder.domain.usecase.GetNewsFromNewsApiUseCase
import com.app.newsfinder.domain.usecase.GetNewsFromNewsDataUseCase
import com.app.newsfinder.presentation.news.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val getNewsFromNewsApiUseCase: GetNewsFromNewsApiUseCase,
    private val getNewsFromNewsDataUseCase: GetNewsFromNewsDataUseCase
) : ViewModel() {

    val newsList = MutableLiveData<List<Article>>()
    var country: String? = null
    var category: String? = null
    val progressStatus = MutableLiveData(View.GONE)
    var query = ""

    val onCountriesSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            adapterView: AdapterView<*>?,
            view: View?,
            position: Int,
            l: Long
        ) {
            country = if (position != 0) {
                adapterView?.selectedItem.toString()
            } else {
                null
            }
            getNews()
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {
            return
        }
    }
    val onCategoriesSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(
            adapterView: AdapterView<*>?,
            view: View?,
            position: Int,
            l: Long
        ) {
            category = if (position != 0) {
                adapterView?.selectedItem.toString()
            } else {
                null
            }
            getNews()
        }

        override fun onNothingSelected(adapterView: AdapterView<*>?) {
            return
        }
    }

    fun getNews() {
        viewModelScope.launch(getExceptionHandler()) {
            progressStatus.value = View.VISIBLE
            val one = async { getNewsFromDataApi() }
            val two = async { getNewsFromNesData() }
            newsList.value = one.await() + two.await()
            progressStatus.value = View.GONE
        }
    }

    private suspend fun getNewsFromDataApi() = getNewsFromNewsApiUseCase.execute(
        GetNewsFromNewsApiUseCase.Params(
            query = query,
            category = category,
            country = country
        )
    )
        .map {
            Article(
                title = it.title ?: "",
                description = it.description ?: "",
                image = it.urlToImage ?: "",
                date = it.publishedAt ?: "",
                author = it.author ?: "",
                url = it.url ?: ""
            )
        }

    private suspend fun getNewsFromNesData() = getNewsFromNewsDataUseCase.execute(
        GetNewsFromNewsDataUseCase.Params(
            query = query,
            category = category,
            country = country
        )
    ).map {
        Article(
            title = it.title ?: "",
            description = it.description ?: "",
            image = it.image_url ?: "",
            date = it.pubDate ?: "",
            author = it.creator?.first() ?: "",
            url = it.link ?: ""
        )
    }


    private fun getExceptionHandler(): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            Log.e("exception is : ", throwable.message.toString())
            progressStatus.value = View.GONE
        }
    }


}