package com.example.articleapp.repository

import com.example.articleapp.data.remote.api.service.APIService
import com.example.articleapp.lib.api.model.ApiArticlesResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
  private val api: APIService
): NewsRepository {

  override suspend fun getNewsList(source: String): Flow<ApiArticlesResponse> {
    return withContext(Dispatchers.IO) {
      flow {emit( api.getNewsList(source) ) }
    }
  }
}