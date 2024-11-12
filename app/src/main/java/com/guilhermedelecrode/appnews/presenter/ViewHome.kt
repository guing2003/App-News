package com.guilhermedelecrode.appnews.presenter

import com.guilhermedelecrode.appnews.model.Article

interface ViewHome {

    interface View{
        //Método para chamar a progressBar assim que a requisição dos artigos acontecer
        fun showProgressBar()
        //Mostrar para gente a mensagem de erro
        fun showFailure(message: String)
        //Para fechar a progressBar
        fun hideProgressBar()
        //Para listar os artigos
        fun showArticles(articles : List<Article>)
    }

    interface Favorite{
        //Para listar os artigos
        fun showArticles(articles : List<Article>)
    }
}