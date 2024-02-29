package com.example.articleapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.articleapp.data.remote.api.model.ArticlesResponse
import com.example.articleapp.databinding.ListItemNewsBinding
import com.example.articleapp.databinding.ListItemNewsCategoryBinding
import com.example.articleapp.ui.model.NewsCategory

class NewsCategoryAdapter(private val clickListener: (NewsCategory) -> Unit) :
    ListAdapter<NewsCategory, NewsCategoryAdapter.NewsViewHolder>(DiffCallback) {
  private lateinit var context: Context
  private val viewBinding: ListItemNewsCategoryBinding
    get() = _viewBinding!!

  private var _viewBinding: ListItemNewsCategoryBinding? = null

  companion object DiffCallback : DiffUtil.ItemCallback<NewsCategory>() {
    override fun areItemsTheSame(oldItem: NewsCategory, newItem: NewsCategory): Boolean {
      return oldItem.categoryName == newItem.categoryName
    }

    override fun areContentsTheSame(oldItem: NewsCategory, newItem: NewsCategory): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
    _viewBinding =
      ListItemNewsCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    context = parent.context
    return NewsViewHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    val category = getItem(position)

    holder.itemView.setOnClickListener{
      clickListener(category)
    }
    holder.bind(category)
  }

  class NewsViewHolder(private val viewDataBinding: ListItemNewsCategoryBinding) :
      RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(category: NewsCategory) {
      viewDataBinding.tvCategory.text = category.categoryName
    }
  }
}