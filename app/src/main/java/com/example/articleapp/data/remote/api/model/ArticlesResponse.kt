package com.example.articleapp.data.remote.api.model

import com.example.articleapp.lib.api.model.ArticleSourceResponse
import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
  @SerializedName("source") val articleSourceResponse: ArticleSourceResponse? = null,
  @SerializedName("author") val author: String? = "",
  @SerializedName("title") val title: String? = "",
  @SerializedName("description") val description: String? = "",
  @SerializedName("url") val url: String? = "",
  @SerializedName("urlToImage") val urlToImage: String? = "",
  @SerializedName("publishedAt") val publishedAt: String? = "",
  @SerializedName("content") val content: String? = ""
)
