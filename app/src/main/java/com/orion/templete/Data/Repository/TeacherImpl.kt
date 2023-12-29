package com.orion.templete.Data.Repository

import android.util.Log
import com.orion.templete.Data.Model.TeacherListDTO
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Domain.Repository.TeacherRepo
import com.orion.templete.util.SafeApiRequest
import javax.inject.Inject


class TeacherImpl @Inject constructor(private val apiService: ApiService) :
        TeacherRepo, SafeApiRequest() {
    override suspend fun getTeacher(): TeacherListDTO {
        val Response = safeApiRequest {
            apiService.getAllBooks()
        }
        return Response
    }
    override suspend fun getTeacherByName(name:String): TeacherListDTO {
        val Response = safeApiRequest {
            apiService.searchByName(name)
        }
        return Response
    }

    override suspend fun updateTeacher(key: String, updatedReviewBook: TeacherDTO): TeacherDTO {
        val Response = safeApiRequest {
            apiService.sendReview(key ,updatedReviewBook )
        }
        return Response
    }


}

