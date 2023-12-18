package com.orion.templete.Data.Model

data class Review(
    val bookId: String,
    val date: String,
    val reviewStart: String,
    val reviewText: String,
    val userId: String,
    val verified: Boolean
)