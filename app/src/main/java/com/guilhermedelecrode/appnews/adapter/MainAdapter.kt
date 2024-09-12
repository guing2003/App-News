package com.guilhermedelecrode.appnews.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.guilhermedelecrode.appnews.R
import com.guilhermedelecrode.appnews.model.Article

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallBack = object : DiffUtil.ItemCallback<Article>() {
        //Verifica se dois itens representam o mesmo objeto ou não
        override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {

            return oldItem.url == newItem.url
        }

        //Verifica se os dois itens possuim o mesmo dado ou conteudo
        override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)

    //Cria a View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder =
        ArticleViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_news,
                    parent,
                    false
                ) //Item view inflado para exibir no recycler view
        )

    //Verificar o tamanho da lista de itens
    override fun getItemCount(): Int = differ.currentList.size

    //Linkar os dados do item
    override fun onBindViewHolder(holder: MainAdapter.ArticleViewHolder, position: Int) {
        val article = differ.currentList[position]
        holder.itemView.apply {
            //Precisei fazer esta alteração --- Diferente do Professor
            //Variaveis que precise fazer para funcionar
            val imageView = findViewById<ImageView>(R.id.ivArticleImage)
            val textViewTitle = findViewById<TextView>(R.id.tvTitle)
            val textViewSource = findViewById<TextView>(R.id.tvSource)
            val textViewDescription = findViewById<TextView>(R.id.tvDescription)
            val textViewPublishedAt = findViewById<TextView>(R.id.tvPublishedAt)


            Glide.with(this).load(article.urlToImage).into(imageView)
            textViewTitle.text = article.author ?: article.source?.name
            textViewSource.text = article.source?.name ?: article.author
            textViewDescription.text = article.description
            textViewPublishedAt.text = article.publishedAt

            setOnClickListener {
                onItemClickListener?.let{ click ->
                    click(article)
                }
            }
        }
    }
    //Serve para poder clicar no artigo
    private var onItemClickListener : ((Article) -> Unit)? = null

    fun setOnClickListener(listener: (Article) -> Unit){
        onItemClickListener = listener
    }
}