package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.exploreDTO

data class ExploreStateHolder(
    val isLoading: Boolean = false,
    val data: exploreDTO? = null,
    val error: String = ""
)
