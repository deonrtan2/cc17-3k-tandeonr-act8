package com.example.bookshelfapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class BooksViewModel(private val repository: BookRepository) : ViewModel() {

    private val _books = MutableLiveData<BookResponse?>()
    val books: LiveData<BookResponse?> get() = _books

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {

                val result = repository.searchBooks(query)
                _books.postValue(result)
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Search failed", e)
                _books.postValue(null)
            }
        }
    }
}
