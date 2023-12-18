package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.BookListDTO

data class BlogsStateHolder(
    val isLoading: Boolean = false,
    val data: BookListDTO? = null,
    val error: String = ""
)
