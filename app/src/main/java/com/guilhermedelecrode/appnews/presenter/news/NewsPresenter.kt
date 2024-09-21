package com.guilhermedelecrode.appnews.presenter.news

import com.guilhermedelecrode.appnews.model.NewsResponse
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.ui.MainActivity


class NewsPresenter(val view : ViewHome.View,
    private val dataSource: NewsDataSource
): NewsHome.Presenter {
    override fun requestAll() {
        this.view.showProgressBar()
        this.dataSource.getBreakingNews(this)

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