package com.guilhermedelecrode.appnews.model.data

import android.content.Context
import android.util.Log
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.db.ArticleDataBase
import com.guilhermedelecrode.appnews.network.RetrofitInstance
import com.guilhermedelecrode.appnews.presenter.favorite.FavoriteHome
import com.guilhermedelecrode.appnews.presenter.news.NewsHome
import com.guilhermedelecrode.appnews.presenter.search.SearchHome
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NewsDataSource(context : Context){

    private var db : ArticleDataBase = ArticleDataBase(context)
    private var newsRepository : NewsRepository = NewsRepository(db)

    fun getBreakingNews(callBack: NewsHome.Presenter) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = RetrofitInstance.api.getBreakingNews("us")
                withContext(Dispatchers.Main) {
                    if (response.isSuccessful) {
                        response.body()?.let { newsResponse ->
                            callBack.onSucces(newsResponse)
                        }
                    } else {
                        callBack.onError(response.message())
                    }
                    callBack.onComplete()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    callBack.onError(e.message ?: "Unknown error")
                    callBack.onComplete()
                }
                Log.e("getBreakingNews", "Error fetching news", e)
            }
        }
    }
    //Function para pesquisar as noticias
    fun searchNews(term : String, callBack: SearchHome.Presenter){
        GlobalScope.launch (Dispatchers.Main){
            val response = RetrofitInstance.api.searchNews(term)
            if(response.isSuccessful){
                response.body()?.let { newsResponse ->
                    callBack.onSucces(newsResponse)
                }
                callBack.onComplete()
            }else{
                callBack.onError(response.message())
                callBack.onComplete()
            }
        }
    }

    //Para salvar o artigo
    fun saveArticle(article: Article){
        GlobalScope.launch(Dispatchers.Main){
            newsRepository.updateInsert(article)
        }
    }
    //pegar todas as news que foram salvas
    fun getAllArticle(callback: FavoriteHome.Presenter){
        var allArticle: List<Article>
        CoroutineScope(Dispatchers.IO).launch {
            allArticle = newsRepository.getAll()

            withContext(Dispatchers.Main){
                callback.onSucces(allArticle)
            }
        }
    }

    fun deleteArticle(article: Article?){
        GlobalScope.launch(Dispatchers.Main) {
            article?.let { articleSafe ->
                newsRepository.delete(articleSafe)
            }
        }
    }
}