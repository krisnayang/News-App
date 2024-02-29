package com.example.articleapp.lib.api.model

import com.google.gson.annotations.SerializedName

data class ArticleSourceResponse(
  @SerializedName("name") val name: String? = ""
)
