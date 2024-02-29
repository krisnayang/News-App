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
class SearchViewModel @Inject constructor(
  private val newsRepository: NewsRepository
) : ViewModel() {


  private var _articles: MutableStateFlow<UiState> = MutableStateFlow(Loading)
  val articles = _articles

  fun searchNews(s: String) = viewModelScope.launch {
    try {
      _articles.value = Loading
      newsRepository.searchNews(s).collect { articles ->
        _articles.value = Success(value = articles.articles.orEmpty())
      }
    }catch (e: Exception) {
      _articles.value = Error(errorMessage = e.toString())
    }
  }
}