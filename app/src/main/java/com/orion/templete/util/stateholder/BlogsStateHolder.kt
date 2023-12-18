package com.orion.templete.util.stateholder

data class BlogsStateHolder(
    val isLoading: Boolean = false,
    val data: BlogDTO? = null,
    val error: String = ""
)
