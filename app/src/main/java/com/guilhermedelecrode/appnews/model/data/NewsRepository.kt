package com.guilhermedelecrode.appnews.model.data

import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.db.ArticleDataBase
import kotlinx.coroutines.flow.Flow

class NewsRepository(private val db : ArticleDataBase) {

    suspend fun updateInsert(article: Article) = db.getArticleDAO().updateInsert(article)

    fun getAll(): List<Article> = db.getArticleDAO().getAll()

    suspend fun delete(article: Article) = db.getArticleDAO().delete(article)
}