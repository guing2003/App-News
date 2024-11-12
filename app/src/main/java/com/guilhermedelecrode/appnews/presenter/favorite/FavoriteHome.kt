package com.guilhermedelecrode.appnews.presenter.favorite

import com.guilhermedelecrode.appnews.model.Article
import kotlinx.coroutines.flow.Flow

interface FavoriteHome {

    interface Presenter {
        fun onSucces(articles: List<Article>)
    }
}