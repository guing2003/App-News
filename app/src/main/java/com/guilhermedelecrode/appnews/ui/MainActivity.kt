package com.guilhermedelecrode.appnews.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.adapter.MainAdapter
import com.guilhermedelecrode.appnews.databinding.ActivityMainBinding
import com.guilhermedelecrode.appnews.model.Article
import com.guilhermedelecrode.appnews.model.data.NewsDataSource
import com.guilhermedelecrode.appnews.presenter.ViewHome
import com.guilhermedelecrode.appnews.presenter.news.NewsPresenter

class MainActivity : AppCompatActivity(), ViewHome.View {

    private val mainAdapter by lazy {
        MainAdapter()
    }
    private lateinit var presenter: NewsPresenter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        Log.d("ArticleActivity", "ViewBinding initialized")

        val view = binding.root
        setContentView(view)

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
        with(binding.rvNews) {
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
        binding.rvProgressBar.visibility = View.VISIBLE
    }

    override fun showFailure(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun hideProgressBar() {
        binding.rvProgressBar.visibility = View.GONE
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