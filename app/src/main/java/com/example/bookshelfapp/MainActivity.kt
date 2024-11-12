package com.example.bookshelfapp

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bookshelfapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BooksViewModel by viewModels { BooksViewModelFactory(BookRepository()) }
    private val bookAdapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.adapter = bookAdapter

        viewModel.books.observe(this, Observer { response ->
            val books = response?.items?.takeIf { it.isNotEmpty() }
            bookAdapter.submitList(books ?: emptyList())
        })

        binding.searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(editable: Editable?) {
                editable?.let {
                    viewModel.searchBooks(it.toString())
                }
            }
        })
    }
}
