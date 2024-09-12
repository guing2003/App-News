package com.guilhermedelecrode.appnews.presenter.search

import com.guilhermedelecrode.appnews.model.NewsResponse

interface SearchHome {
    interface Presenter{
        fun serach(term : String)
        //metodo de sucesso para trazer o newsResponse
        fun onSucces(newsResponse : NewsResponse)
        //metodo de erro para exibir a mensagem
        fun onError(message : String)
        // metodo para ser executado em caso de success e error, apenas para fechar a progressBar
        fun onComplete()
    }
}