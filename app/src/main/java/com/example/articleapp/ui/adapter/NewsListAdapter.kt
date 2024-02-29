package com.example.articleapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.articleapp.databinding.ListItemNewsBinding
import com.example.articleapp.data.remote.api.model.ArticlesResponse

class NewsListAdapter(): ListAdapter<ArticlesResponse, NewsListAdapter.NewsViewHolder>(DiffCallback) {
  private lateinit var context: Context
  private val viewBinding: ListItemNewsBinding
    get() = _viewBinding!!

  private var _viewBinding: ListItemNewsBinding? = null

  companion object DiffCallback: DiffUtil.ItemCallback<ArticlesResponse>() {
    override fun areItemsTheSame(oldItem: ArticlesResponse, newItem: ArticlesResponse): Boolean {
      return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ArticlesResponse, newItem: ArticlesResponse): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    _viewBinding = ListItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    context = parent.context
    return NewsViewHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    val articles = getItem(position)

    holder.bind(articles)
  }

  class NewsViewHolder(private val viewDataBinding: ListItemNewsBinding) :
      RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(articles: ArticlesResponse){
      viewDataBinding.tvAuthor.text = articles.author
      viewDataBinding.tvTitle.text = articles.title
    }
  }
}