package com.guilhermedelecrode.appnews.adapter

import android.icu.text.ListFormatter.Width
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
import com.guilhermedelecrode.appnews.databinding.ItemNewsBinding
import com.guilhermedelecrode.appnews.model.Article

class MainAdapter : RecyclerView.Adapter<MainAdapter.ArticleViewHolder>() {

    inner class ArticleViewHolder(val binding: ItemNewsBinding) : RecyclerView.ViewHolder(binding.root)

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
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),parent, false
            )
        )

    //Verificar o tamanho da lista de itens
    override fun getItemCount(): Int = differ.currentList.size

    //Linkar os dados do item
    override fun onBindViewHolder(holder: MainAdapter.ArticleViewHolder, position: Int) {


        with(holder){
            with(differ.currentList[position]){
                Glide.with(holder.itemView.context).load(urlToImage).into(binding.ivArticleImage)
                binding.tvTitle.text = author ?: source?.name
                binding.tvSource.text = source?.name ?: author
                binding.tvDescription.text = description
                binding.tvPublishedAt.text = publishedAt

                holder.itemView.setOnClickListener{
                    onItemClickListener?.let {click ->
                        click(this)
                    }
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