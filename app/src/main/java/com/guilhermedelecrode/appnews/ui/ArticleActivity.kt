package com.guilhermedelecrode.appnews.ui

import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource

class ArticleActivity : AbstractActivity() {

    private lateinit var webView : WebView
    private lateinit var fab : FloatingActionButton

    private lateinit var article: Article

    override fun getLayout(): Int = R.layout.activity_article

    override fun onInject() {

        webView = findViewById(R.id.webView)
        fab = findViewById(R.id.fab)

        getArticle()
        val dataSource = NewsDataSource(this)

        webView.apply {
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            article.url?.let {url ->
                loadUrl(url)
            }
        }

        fab.setOnClickListener{
            dataSource.saveArticle(article)
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
}