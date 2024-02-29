package com.example.articleapp.lib.api.model

import com.example.articleapp.data.remote.api.model.ArticlesResponse
import com.google.gson.annotations.SerializedName

data class ApiArticlesResponse(
  @SerializedName("totalResults") val totalResults: Int? = 0,
  @SerializedName("articles") val articles: List<ArticlesResponse>?
)
