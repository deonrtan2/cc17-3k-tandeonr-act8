package com.example.bookshelfapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BooksViewModelFactory(
    private val repository: BookRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BooksViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BooksViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
