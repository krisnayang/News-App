package com.example.articleapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articleapp.R
import com.example.articleapp.data.remote.api.model.ArticlesResponse
import com.example.articleapp.databinding.LayoutFragmentNewsListBinding
import com.example.articleapp.ui.adapter.NewsListAdapter
import com.example.articleapp.ui.state.Error
import com.example.articleapp.ui.state.Loading
import com.example.articleapp.ui.state.Success
import com.example.articleapp.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsListFragment: Fragment(R.layout.layout_fragment_news_list) {

  private val navigationArgs: NewsListFragmentArgs by navArgs()

  private val viewBinding: LayoutFragmentNewsListBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentNewsListBinding? = null

  private var newsAdapter: NewsListAdapter? = null

  private val viewModel by viewModels<NewsViewModel>()

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    val source = navigationArgs.source

    _viewBinding = LayoutFragmentNewsListBinding.inflate(inflater, container, false)
    newsAdapter = NewsListAdapter { item ->
      val action = NewsListFragmentDirections.actionNewsListFragmentToNewsDetailFragment(item.url.orEmpty())
      findNavController().navigate(action)
    }

    viewModel.getNewsList(source)
    setupObserver(viewModel)
    viewBinding.recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = newsAdapter
    }

    return viewBinding.root
  }

  private fun setupObserver(viewModel: NewsViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.totalResults.collect { state ->
            val text = when (state) {
              is Loading -> {
                "Loading"
              }

              is Error -> {
                state.errorMessage
              }

              is Success<*> -> {
                (state.value as Int).toString()
              }
            }
            viewBinding.totalResults.text = text
          }
        }
        launch {
          viewModel.articles.collect { state ->
            when (state) {
              is Loading -> {
                viewBinding.loader.text = "Loading"
                viewBinding.recyclerView.visibility = View.GONE
              }

              is Error -> {
                viewBinding.loader.text = "Error Found"
                viewBinding.recyclerView.visibility = View.GONE
              }

              is Success<*> -> {
                viewBinding.loader.text = "Success"
                viewBinding.recyclerView.visibility = View.VISIBLE
                newsAdapter?.submitList((state.value as List<ArticlesResponse>))
              }
            }
          }
        }
      }
    }
  }
}