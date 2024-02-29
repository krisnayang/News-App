package com.example.articleapp.repository

import com.example.articleapp.data.remote.api.model.ApiSourcesResponse
import com.example.articleapp.data.remote.api.service.APIService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
  private val api: APIService
): SourcesRepository {
  override suspend fun getSourcesByCategory(category: String): Flow<ApiSourcesResponse> {
    return withContext(Dispatchers.IO) {
      flow {emit( api.getSourcesByCategory(category) ) }
    }
  }

}