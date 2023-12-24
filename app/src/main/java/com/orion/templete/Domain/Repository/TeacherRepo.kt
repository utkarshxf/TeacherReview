package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.TeacherListDTO
import com.orion.templete.Data.Model.TeacherDTO

interface TeacherRepo {

    suspend fun getTeacher(): TeacherListDTO

    suspend fun updateTeacher(key : String, updatedReviewBook : TeacherDTO):TeacherDTO

}