package com.example.articleapp.repository

import com.example.articleapp.lib.api.model.ApiArticlesResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
  suspend fun getNewsList(source: String): Flow<ApiArticlesResponse>

  suspend fun searchNews(source: String): Flow<ApiArticlesResponse>
}