package com.example.articleapp.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articleapp.MainActivity
import com.example.articleapp.R
import com.example.articleapp.data.remote.api.model.ArticlesResponse
import com.example.articleapp.databinding.LayoutFragmentSearchBinding
import com.example.articleapp.databinding.LayoutNewsCategoryBinding
import com.example.articleapp.ui.adapter.NewsCategoryAdapter
import com.example.articleapp.ui.adapter.NewsListAdapter
import com.example.articleapp.ui.model.NewsCategory
import com.example.articleapp.ui.state.Error
import com.example.articleapp.ui.state.Loading
import com.example.articleapp.ui.state.Success
import com.example.articleapp.viewmodel.NewsViewModel
import com.example.articleapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment: Fragment(R.layout.layout_fragment_search) {


  val viewModel by viewModels<SearchViewModel>()
  private val viewBinding: LayoutFragmentSearchBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentSearchBinding? = null

  private var searchJob: Job? = null

  private var newsAdapter: NewsListAdapter? = null

  private var type: String = ""

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
  }

  override fun onStart() {
    super.onStart()
    getCurrentActivity()?.getBottomNav()?.visibility = View.VISIBLE
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {

    _viewBinding = LayoutFragmentSearchBinding.inflate(inflater, container, false)
    newsAdapter = NewsListAdapter { item ->
      val action = SearchFragmentDirections.actionSearchFragmentToNewsDetailFragment(item.url.orEmpty())
      findNavController().navigate(action)
    }
    setupObserver(viewModel)
    viewBinding.recyclerView.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = newsAdapter
    }


    viewBinding.etSearchMovie.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

      override fun afterTextChanged(s: Editable?) {
        searchJob?.cancel()
        searchJob = viewLifecycleOwner.lifecycleScope.launch {
          viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
            launch {
              delay(3000)
              viewModel.searchNews(s.toString())
            }
          }
        }
      }
    })

    return viewBinding.root
  }

  private fun setupObserver(viewModel: SearchViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.articles.collect { state ->
            when (state) {
              is Loading -> {
                viewBinding.loader.text = "Search"
                viewBinding.recyclerView.visibility = View.GONE
              }

              is Error -> {
                viewBinding.loader.text = state.errorMessage
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

  private fun getCurrentActivity(): MainActivity?{
    return (activity as? MainActivity)
  }
}