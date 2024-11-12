package com.guilhermedelecrode.appnews.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilhermedelecrode.appnews.adapter.MainAdapter
import com.guilhermedelecrode.appnews.databinding.ActivitySearchBinding
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.presenter.search.SearchPresenter
import com.guilhermedelecrode.appnews.util.UtilQueryTextListener

class SearchActivity : AppCompatActivity(), ViewHome.View{

    private val mainAdapter by lazy{
        MainAdapter()
    }
    private lateinit var presenter : SearchPresenter
    //ViewBinding
    private lateinit var binding : ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        Log.d("ArticleActivity", "ViewBinding initialized")

        val view = binding.root
        setContentView(view)



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
        binding.searchNews.setOnQueryTextListener(
            UtilQueryTextListener(
                this.lifecycle
            ){
                newText ->
                newText?.let {query ->
                    if(query.isNotEmpty()){
                        presenter.search(query)
                        binding.rvProgressBarSearch.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    private fun configRecycler() {
        with(binding.rvSearch) {
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
        binding.rvProgressBarSearch.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.rvProgressBarSearch.visibility = View.INVISIBLE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
    }

}