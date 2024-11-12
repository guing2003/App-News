package com.guilhermedelecrode.appnews.presenter.favorite

import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome

class FavoritePresenter(val view: ViewHome.Favorite, private val dataSource: NewsDataSource): FavoriteHome.Presenter {

    fun getAll(){
        this.dataSource.getAllArticle(this)
    }

    fun saveArticle(article: Article){
        this.dataSource.saveArticle(article)
    }

    fun deleteArticle(article: Article){
        this.dataSource.deleteArticle(article)
    }

    override fun onSucces(articles: List<Article>){
        this.view.showArticles(articles)
    }

}