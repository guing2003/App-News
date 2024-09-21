package com.guilhermedelecrode.appnews.ui

import android.content.Intent
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.adapter.MainAdapter
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.presenter.search.SearchPresenter
import com.guilhermedelecrode.appnews.util.UtilQueryTextListener

class SearchActivity : AbstractActivity(), ViewHome.View{

    lateinit var searchNews : SearchView
    lateinit var rvProgressBarSearch : ProgressBar
    lateinit var rvSearch: RecyclerView

    private val mainAdapter by lazy{
        MainAdapter()
    }
    private lateinit var presenter : SearchPresenter


    override fun getLayout(): Int = R.layout.activity_search

    override fun onInject() {

        rvSearch = findViewById(R.id.rvSearch)
        searchNews = findViewById(R.id.searchNews)
        rvProgressBarSearch = findViewById(R.id.rvProgressBarSearch)

        val dataSource = NewsDataSource(this)
        presenter = SearchPresenter(this, dataSource)
        configRecycler()
        search()
        clickAdapter()
    }

    private fun clickAdapter(){
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    private fun search(){
        searchNews.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle
            ){
                newText ->
                newText?.let {query ->
                    if(query.isNotEmpty()){
                        presenter.search(query)
                        rvProgressBarSearch.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun configRecycler() {
        with(rvSearch) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@SearchActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@SearchActivity,DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showProgressBar() {
        rvProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

}