package com.example.bookshelfapp

import android.graphics.Rect
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookshelfapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: BooksViewModel by viewModels { BooksViewModelFactory(BookRepository()) }
    private val bookAdapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

       
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

       
        val spanCount = 2
        binding.recyclerView.layoutManager = GridLayoutManager(this, spanCount)
        binding.recyclerView.adapter = bookAdapter

        
        binding.recyclerView.addItemDecoration(
            object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
                    val spacing = resources.getDimensionPixelSize(R.dimen.item_spacing)
                    outRect.set(spacing, spacing, spacing, spacing)
                }
            }
        )

      
        viewModel.books.observe(this) { response ->
            val books = response?.items?.takeIf { it.isNotEmpty() }
            bookAdapter.submitList(books ?: emptyList())
        }

    
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
