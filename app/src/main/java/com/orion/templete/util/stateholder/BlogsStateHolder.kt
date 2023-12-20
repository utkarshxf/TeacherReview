package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO

data class BlogsStateHolder(
    val isLoading: Boolean = false,
    val data: BookListDTO? = null,
    val error: String = ""
)

data class bookUpdatedValue(
    val isLoading: Boolean = false,
    val data: BooksDTO? = null,
    val error: String = ""
)

