package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.MintDTO

data class MintsStateHolder(
    val isLoading: Boolean = false,
    val data: MintDTO? = null,
    val error: String = ""
)
