package com.example.articleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articleapp.repository.NewsRepository
import com.example.articleapp.ui.state.Error
import com.example.articleapp.ui.state.Loading
import com.example.articleapp.ui.state.Success
import com.example.articleapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
  private val newsRepository: NewsRepository
) : ViewModel() {

  private var _articles: MutableStateFlow<UiState> = MutableStateFlow(Loading)
  val articles = _articles

  private var _totalResults: MutableStateFlow<UiState> = MutableStateFlow(Loading)
  val totalResults = _totalResults

  fun getNewsList(source: String) = viewModelScope.launch {
    try {
      _articles.value = Loading
      _totalResults.value = Loading
      newsRepository.getNewsList(source).collect { articles ->
        _articles.value = Success(value = articles.articles.orEmpty())
        _totalResults.value = Success(value = articles.totalResults ?: 0)
      }
    }catch (e: Exception) {
      _articles.value = Error(errorMessage = e.toString())
      _totalResults.value = Error(errorMessage = e.toString())
    }
  }
}