package com.guilhermedelecrode.appnews.presenter.news

import com.guilhermedelecrode.appnews.model.NewsResponse

interface NewsHome {
    interface Presenter{
        //Metodo responsavel por pegar todas as noticias na lista
        fun requestAll()
        //metodo de sucesso para trazer o newsResponse
        fun onSucces(newsResponse : NewsResponse)
        //metodo de erro para exibir a mensagem
        fun onError(message : String)
        // metodo para ser executado em caso de success e error, apenas para fechar a progressBar
        fun onComplete()
    }
}