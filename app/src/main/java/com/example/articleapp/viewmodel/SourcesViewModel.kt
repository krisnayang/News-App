package com.example.articleapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.articleapp.repository.SourcesRepository
import com.example.articleapp.ui.state.Error
import com.example.articleapp.ui.state.Loading
import com.example.articleapp.ui.state.Success
import com.example.articleapp.ui.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
  private val sourcesRepository: SourcesRepository
) : ViewModel() {

  private var _sources: MutableStateFlow<UiState> = MutableStateFlow(Loading)
  val sources = _sources

  fun getSourcesByCategory(category: String) = viewModelScope.launch {
    try {
      _sources.value = Loading
      sourcesRepository.getSourcesByCategory(category).collect { sources ->
        _sources.value = Success(value = sources.sources.orEmpty())
      }
    }catch (e: Exception) {
      _sources.value = Error(errorMessage = e.toString())
    }
  }
}