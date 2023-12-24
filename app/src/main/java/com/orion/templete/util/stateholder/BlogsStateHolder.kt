package com.orion.templete.util.stateholder

import com.orion.templete.Data.Model.TeacherListDTO
import com.orion.templete.Data.Model.TeacherDTO

data class BlogsStateHolder(
    val isLoading: Boolean = false,
    val data: TeacherListDTO? = null,
    val error: String = ""
)

data class bookUpdatedValue(
    val isLoading: Boolean = false,
    val data: TeacherDTO? = null,
    val error: String = ""
)

