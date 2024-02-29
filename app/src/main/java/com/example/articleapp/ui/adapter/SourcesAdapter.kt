package com.example.articleapp.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.articleapp.data.remote.api.model.SourcesResponse
import com.example.articleapp.databinding.ListItemSourcesBinding
import com.example.articleapp.ui.model.NewsCategory

class SourcesAdapter(private val clickListener: (SourcesResponse) -> Unit) :
    ListAdapter<SourcesResponse, SourcesAdapter.SourcesViewHolder>(DiffCallback) {
  private lateinit var context: Context
  private val viewBinding: ListItemSourcesBinding
    get() = _viewBinding!!

  private var _viewBinding: ListItemSourcesBinding? = null

  companion object DiffCallback : DiffUtil.ItemCallback<SourcesResponse>() {
    override fun areItemsTheSame(oldItem: SourcesResponse, newItem: SourcesResponse): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SourcesResponse, newItem: SourcesResponse): Boolean {
      return oldItem == newItem
    }
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourcesViewHolder {
    _viewBinding =
      ListItemSourcesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    context = parent.context
    return SourcesViewHolder(viewBinding)
  }

  override fun onBindViewHolder(holder: SourcesViewHolder, position: Int) {
    val source = getItem(position)

    holder.itemView.setOnClickListener{
      clickListener(source)
    }
    holder.bind(source)
  }

  class SourcesViewHolder(private val viewDataBinding: ListItemSourcesBinding) :
      RecyclerView.ViewHolder(viewDataBinding.root) {
    fun bind(source: SourcesResponse) {
      viewDataBinding.tvName.text = source.name
      viewDataBinding.tvDescription.text = source.description
      viewDataBinding.tvUrl.text = source.url
      viewDataBinding.tvLanguage.text = source.language
      viewDataBinding.tvCountry.text = source.country
    }
  }
}