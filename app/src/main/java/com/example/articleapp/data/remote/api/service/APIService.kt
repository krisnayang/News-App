package com.example.articleapp.data.remote.api.service

import com.example.articleapp.data.remote.api.model.ApiSourcesResponse
import com.example.articleapp.lib.api.model.ApiArticlesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

  @GET("top-headlines?sources?&apiKey=5558267533304e39a3752a82d19a21c6") suspend fun getNewsList(
    @Query("sources") source: String,
  ): ApiArticlesResponse

  @GET("top-headlines/sources?&apiKey=5558267533304e39a3752a82d19a21c6")
  suspend fun getSourcesByCategory(@Query("category") category: String): ApiSourcesResponse

  @GET("everything") suspend fun searchNews(
    @Query("q") s: String,
    @Query("apiKey") apikey: String = "5558267533304e39a3752a82d19a21c6",
  ): ApiArticlesResponse
}