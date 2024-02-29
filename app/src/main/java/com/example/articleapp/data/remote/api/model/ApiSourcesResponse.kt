package com.example.articleapp.data.remote.api.model

import com.google.gson.annotations.SerializedName

data class ApiSourcesResponse(
  @SerializedName("status") val status: String? = "",
  @SerializedName("sources") val sources: List<SourcesResponse>?
)
