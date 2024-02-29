package com.example.articleapp.repository

import com.example.articleapp.data.remote.api.model.ApiSourcesResponse
import kotlinx.coroutines.flow.Flow

interface SourcesRepository {
  suspend fun getSourcesByCategory(category: String): Flow<ApiSourcesResponse>
}