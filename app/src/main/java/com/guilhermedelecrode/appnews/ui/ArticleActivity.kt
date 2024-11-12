package com.guilhermedelecrode.appnews.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.presenter.favorite.FavoritePresenter
import com.guilhermedelecrode.appnews.presenter.news.NewsHome

class ArticleActivity : AbstractActivity(), ViewHome.Favorite {

    private lateinit var webView : WebView
    private lateinit var fab : FloatingActionButton

    private lateinit var article: Article
    private lateinit var presenter: FavoritePresenter

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {

        webView = findViewById(R.id.webView)
        fab = findViewById(R.id.fab)

        getArticle()
        val dataSource = NewsDataSource(this)
        presenter = FavoritePresenter(this, dataSource)

        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            article.url?.let {url ->
                loadUrl(url)
            }
        }

        fab.setOnClickListener{
            presenter.saveArticle(article)
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