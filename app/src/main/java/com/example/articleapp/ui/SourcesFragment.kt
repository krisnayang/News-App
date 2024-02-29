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
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.articleapp.R
import com.example.articleapp.data.remote.api.model.ArticlesResponse
import com.example.articleapp.data.remote.api.model.SourcesResponse
import com.example.articleapp.databinding.LayoutFragmentSourcesBinding
import com.example.articleapp.lib.api.model.ArticleSourceResponse
import com.example.articleapp.ui.adapter.SourcesAdapter
import com.example.articleapp.ui.state.Error
import com.example.articleapp.ui.state.Loading
import com.example.articleapp.ui.state.Success
import com.example.articleapp.viewmodel.SourcesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SourcesFragment: Fragment(R.layout.layout_fragment_sources) {

  private val viewBinding: LayoutFragmentSourcesBinding
    get() = _viewBinding!!

  private var _viewBinding: LayoutFragmentSourcesBinding? = null

  private var sourcesAdapter: SourcesAdapter? = null

  private val viewModel by viewModels<SourcesViewModel>()

  override fun onStart() {
    super.onStart()
  }

  override fun onCreateView(
    inflater: LayoutInflater, container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    _viewBinding = LayoutFragmentSourcesBinding.inflate(inflater, container, false)
    sourcesAdapter = SourcesAdapter { category ->
      //TODO: add parameter
      val action = SourcesFragmentDirections.actionSourcesFragmentToNewsListFragment()
      findNavController().navigate(action)
    }

    return viewBinding.root
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    val category = "business"

    viewModel.getSourcesByCategory(category)
    setupObserver(viewModel)
    viewBinding.rvSources.apply {
      layoutManager = LinearLayoutManager(context)
      adapter = sourcesAdapter
    }
  }

  private fun setupObserver(viewModel: SourcesViewModel) {
    viewLifecycleOwner.lifecycleScope.launch {
      viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        launch {
          viewModel.sources.collect { state ->
            when (state) {
              is Loading -> {
                viewBinding.loader.text = "Loading"
                viewBinding.rvSources.visibility = View.GONE
              }

              is Error -> {
                viewBinding.loader.text = state.errorMessage
                viewBinding.rvSources.visibility = View.GONE
              }

              is Success<*> -> {
                viewBinding.loader.text = "Success"
                viewBinding.rvSources.visibility = View.VISIBLE
                sourcesAdapter?.submitList(state.value as List<SourcesResponse>)
              }
            }
          }
        }
      }
    }
  }
}