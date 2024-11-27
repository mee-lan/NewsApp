package com.example.mynews.data.remote

import com.example.mynews.data.remote.dataTransferObject.NewsResponse
import com.example.mynews.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query


interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query(value = "page") page :Int,
        @Query(value= "sources") sources: String,
        @Query(value = "apiKey") apiKey: String = API_KEY
    ): NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query(value = "q") searchQuery :String,
        @Query(value = "page") page :Int,
        @Query(value= "sources") sources: String,
        @Query(value = "apiKey") apiKey: String = API_KEY
    ): NewsResponse


}