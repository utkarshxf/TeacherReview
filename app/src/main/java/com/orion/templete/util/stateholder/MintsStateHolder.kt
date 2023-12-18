package com.orion.templete.util.stateholder

data class MintsStateHolder(
    val isLoading: Boolean = false,
    val data: MintDTO? = null,
    val error: String = ""
)
