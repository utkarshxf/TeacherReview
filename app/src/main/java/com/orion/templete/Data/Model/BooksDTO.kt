package com.orion.templete.Data.Model

data class BooksDTO(
    val about: String,
    val author: String,
    val bookId: String,
    val imageUrl: String,
    val name: String,
    val review: List<Review>
)