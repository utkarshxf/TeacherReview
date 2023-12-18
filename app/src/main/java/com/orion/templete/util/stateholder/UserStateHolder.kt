package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.PersonDTO


data class UserStateHolder(
    val isLoading: Boolean = false,
    val data: PersonDTO? = null,
    val error: String = ""
)
