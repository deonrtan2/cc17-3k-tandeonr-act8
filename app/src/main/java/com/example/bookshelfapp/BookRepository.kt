package com.example.bookshelfapp

import android.util.Log
import retrofit2.Response

class BookRepository {
    private val api = RetrofitInstance.api


    suspend fun searchBooks(query: String): BookResponse {
        try {

            val response: Response<BookResponse> = api.searchBooks(query)


            if (response.isSuccessful) {
                Log.d("BookRepository", "API Search Response: ${response.body()}")
                return response.body() ?: BookResponse(emptyList())
            } else {
                Log.e("BookRepository", "Search API call failed with status: ${response.code()}")
                throw Exception("Search API call failed with status: ${response.code()}")
            }
        } catch (e: Exception) {
            Log.e("BookRepository", "Search API call failed", e)
            throw e
        }
    }
}
