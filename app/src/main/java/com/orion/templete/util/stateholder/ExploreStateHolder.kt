package com.orion.templete.util.stateholder

data class ExploreStateHolder(
    val isLoading: Boolean = false,
    val data: exploreDTO? = null,
    val error: String = ""
)
