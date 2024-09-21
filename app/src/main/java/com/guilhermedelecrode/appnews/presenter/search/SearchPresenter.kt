package com.guilhermedelecrode.appnews.presenter.search

import com.guilhermedelecrode.appnews.model.NewsResponse
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome

class SearchPresenter(val view : ViewHome.View,
                      private val dataSource: NewsDataSource): SearchHome.Presenter{
    override fun search(term: String) {
        this.view.showProgressBar()
        this.dataSource.searchNews(term, this)
    }

    override fun onSucces(newsResponse: NewsResponse) {
        this.view.showArticles(newsResponse.articles)
    }

    override fun onError(message: String) {
        this.view.showFailure(message)
    }

    override fun onComplete() {
        this.view.hideProgressBar()
    }

}