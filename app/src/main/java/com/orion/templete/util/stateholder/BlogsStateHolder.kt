package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.BlogDTO

data class BlogsStateHolder(
    val isLoading: Boolean = false,
    val data: BlogDTO? = null,
    val error: String = ""
)
