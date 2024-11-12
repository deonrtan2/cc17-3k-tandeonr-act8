package com.example.bookshelfapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.bookshelfapp.databinding.ItemBookBinding

class BookAdapter : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val differ = AsyncListDiffer(this, object : DiffUtil.ItemCallback<Book>() {
        override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
            return oldItem == newItem
        }
    })

    fun submitList(list: List<Book>) {
        differ.submitList(list)
    }

    inner class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            binding.bookTitle.text = book.volumeInfo.title

            val imageUrl = book.volumeInfo.imageLinks?.thumbnail
                ?.replace("http", "https")
            binding.bookImage.load(imageUrl) {
                error(R.drawable.ic_error)
                placeholder(R.drawable.ic_placeholder)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}
