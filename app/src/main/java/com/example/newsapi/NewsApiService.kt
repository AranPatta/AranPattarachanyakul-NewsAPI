package com.example.newsapi


import retrofit2.Call;
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("apiKey") apiKey: String,
        @Query("country") country: String,
        @Query("category") category: String
    ): Response<NewsResponse>
}