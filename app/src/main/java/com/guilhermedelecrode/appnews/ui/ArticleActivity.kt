package com.guilhermedelecrode.appnews.ui

import android.os.Bundle
import android.util.Log

import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.databinding.ActivityArticleBinding
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.presenter.favorite.FavoritePresenter

class ArticleActivity : AppCompatActivity(), ViewHome.Favorite {


    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    private lateinit var binding : ActivityArticleBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityArticleBinding.inflate(layoutInflater)
        Log.d("ArticleActivity", "ViewBinding initialized")

        val view = binding.root
        setContentView(view)

        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)


        binding.webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            article.url?.let {url ->
                loadUrl(url)
            }
        }

        binding.fab.setOnClickListener{
            presenter.saveArticle(article)
            Log.d("ArticleActivity", "Presenter initialized: $presenter")

            Snackbar.make(it, R.string.article_saved_successful,
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun getArticle() {
        intent.extras?.let { articleSend ->
            article = articleSend.get("article") as Article
        }
    }

    override fun showArticles(articles: List<Article>){

    }
}