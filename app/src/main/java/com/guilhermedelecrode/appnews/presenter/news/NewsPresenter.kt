package com.guilhermedelecrode.appnews.presenter.news

import com.guilhermedelecrode.appnews.model.NewsResponse
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import javax.sql.CommonDataSource

class NewsPresenter(val view : ViewHome.View,
    private val dataSource: NewsDataSource
): NewsHome.Presenter {
    override fun requestAll() {
        TODO("Not yet implemented")
    }

    override fun onSucces(newsResponse: NewsResponse) {
        TODO("Not yet implemented")
    }

    override fun onError(message: String) {
        TODO("Not yet implemented")
    }

    override fun onComplete() {
        TODO("Not yet implemented")
    }

}