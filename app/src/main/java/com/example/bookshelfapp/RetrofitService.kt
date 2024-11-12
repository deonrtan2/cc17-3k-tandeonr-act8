package com.example.bookshelfapp

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("volumes")
    suspend fun searchBooks(@Query("q") query: String): Response<BookResponse>
}
