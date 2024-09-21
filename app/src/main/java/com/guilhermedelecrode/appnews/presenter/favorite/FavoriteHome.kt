package com.guilhermedelecrode.appnews.presenter.favorite

import com.guilhermedelecrode.appnews.model.Article
import kotlinx.coroutines.flow.Flow

interface FavoriteHome {

    fun showArticle(articles: List<Article>)
}