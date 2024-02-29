package com.example.articleapp.ui.state

sealed class UiState
object Loading : UiState()
class Error(val errorMessage: String) : UiState()
class Success<T>(val value: T) : UiState()