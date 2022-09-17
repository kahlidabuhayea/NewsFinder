package com.app.newsfinder.presentation.news.view

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.app.newsfinder.R
import com.app.newsfinder.presentation.news.model.Article
import com.app.newsfinder.presentation.news.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity(),NewsAdapter.ItemClickListener {

    private val viewModel: NewsViewModel by viewModels()
    private lateinit var newsRecyclerView: RecyclerView
    private lateinit var countriesSpinner: Spinner
    private lateinit var categoriesSpinner: Spinner
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        handleViews()
        handleObservers()
        viewModel.getNews()
    }

    private fun handleViews() {
        newsRecyclerView = findViewById(R.id.rv_news)
        countriesSpinner = findViewById(R.id.countries)
        categoriesSpinner = findViewById(R.id.categories)
        progressBar = findViewById(R.id.progress)
        countriesSpinner.setSelection(0, false)
        categoriesSpinner.setSelection(0, false)
        countriesSpinner.onItemSelectedListener = viewModel.onCountriesSelectedListener
        categoriesSpinner.onItemSelectedListener = viewModel.onCategoriesSelectedListener
    }

    private fun handleObservers() {
        viewModel.newsList.observe(this) {
            newsRecyclerView.adapter = NewsAdapter(this, it)
            (newsRecyclerView.adapter as NewsAdapter).setClickListener(this)
        }
        viewModel.progressStatus.observe(this) {
            progressBar.visibility = it
        }
    }

    override fun onItemClick(article: Article) {
      if (article.url.isNotEmpty()){
          val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(article.url))
          startActivity(browserIntent)
      }
    }
}