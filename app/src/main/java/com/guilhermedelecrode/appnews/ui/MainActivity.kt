package com.guilhermedelecrode.appnews.ui

import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.adapter.MainAdapter
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.NewsResponse
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.network.NewsAPI
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.presenter.news.NewsPresenter

class MainActivity : AbstractActivity(), ViewHome.View {

    lateinit var rvNews: RecyclerView
    lateinit var rvProgressBar: ProgressBar

    private val mainAdapter by lazy {
        MainAdapter()
    }
    private lateinit var presenter: NewsPresenter

    override fun getLayout(): Int = R.layout.activity_main

    override fun onInject() {

        rvNews = findViewById(R.id.rvNews)
        rvProgressBar = findViewById(R.id.rvProgressBar)

        val dataSource = NewsDataSource(this)
        presenter = NewsPresenter(this, dataSource)
        presenter.requestAll()
        configRecycle()
        clickAdapter()
    }

    private fun clickAdapter(){
        mainAdapter.setOnClickListener { article ->
            val intent = Intent(this, ArticleActivity::class.java)
            intent.putExtra("article", article)
            startActivity(intent)
        }
    }

    private fun configRecycle() {
        with(rvNews) {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
            addItemDecoration(
                DividerItemDecoration(
                    this@MainActivity, DividerItemDecoration.VERTICAL
                )
            )
        }
    }

    override fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        rvProgressBar.visibility = View.GONE
    }

    override fun showArticles(articles: List<Article>) {
        mainAdapter.differ.submitList(articles.toList())
        if (articles.isEmpty()) {
            Toast.makeText(this, "Nenhuma notícia encontrada.", Toast.LENGTH_SHORT).show()
        } else {
            mainAdapter.differ.submitList(articles.toList())
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_menu ->{
                Intent(this, SearchActivity::class.java).apply{
                    startActivity(this)
                }
            }
            R.id.favorite -> {
                Intent(this,FavoriteActivity::class.java).apply {
                    startActivity(this)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}