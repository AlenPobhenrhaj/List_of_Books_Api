package com.example.testapp1

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.example.testapp1.model.BooksApi
import kotlinx.coroutines.launch

//Working code
/*class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listView.adapter = adapter

        lifecycleScope.launch {
            val response = BooksApi().getBestSellers()

            if (response.isSuccessful) {
                val books = response.body()?.results?.flatMap { it.book_details }?.map { it.title } ?: emptyList()
                adapter.addAll(books)
            } else {
                Log.e("testing", "Failed to get best sellers")
            }
        }
    }
}*/

//Working 2nd code, improved with title and author
/*class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listView.adapter = adapter

        lifecycleScope.launch {
            val response = BooksApi().getBestSellers()

            if (response.isSuccessful) {
                val books = response.body()?.results?.flatMap { it.book_details }?.map { "${it.title} by ${it.author}" } ?: emptyList()
                adapter.addAll(books)
            } else {
                Log.e("testing", "Failed to get best sellers")
            }
        }
    }
}*/

//Displays title,author and description
/*class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)
        val titleTextView = findViewById<TextView>(R.id.text_view)
        val descriptionTextView = findViewById<TextView>(R.id.desc_text_view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listView.adapter = adapter

        lifecycleScope.launch {
            val response = BooksApi().getBestSellers()

            if (response.isSuccessful) {
                val books = response.body()?.results?.flatMap { it.book_details } ?: emptyList()
                val bookTitles = books.map { it.title }
                adapter.addAll(bookTitles)

                listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val book = books[position]
                    titleTextView.text = book.title
                    descriptionTextView.text = book.description
                }
            } else {
                Log.e("testing", "Failed to get best sellers")
            }
        }
    }
}*/

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listView.adapter = adapter

        lifecycleScope.launch {
            val response = BooksApi().getBestSellers()

            if (response.isSuccessful) {
                val books = response.body()?.results?.flatMap { it.book_details } ?: emptyList()
                val bookTitles = books.map { it.title }
                adapter.addAll(bookTitles)

                listView.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
                    val intent = Intent(this@MainActivity, BookDetailActivity::class.java)
                    intent.putExtra("title", books[position].title)
                    intent.putExtra("description", books[position].description)
                    startActivity(intent)
                }
            } else {
                Log.e("testing", "Failed to get best sellers")
            }
        }
    }
}

