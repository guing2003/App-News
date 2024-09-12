package com.guilhermedelecrode.appnews.presenter.search

import com.guilhermedelecrode.appnews.model.NewsResponse
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome

class SearchPresenter(val view : ViewHome.View,
                      private val dataSource: NewsDataSource): SearchHome.Presenter{
    override fun serach(term: String) {
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