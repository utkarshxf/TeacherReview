package com.orion.templete.Data.Network

import com.orion.templete.Data.Model.PersonDTO
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.Data.Model.TeacherListDTO
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    //    after ? everything represent query
    @GET("books/")
    suspend fun getAllBooks(
    ): retrofit2.Response<TeacherListDTO>

    @PUT("books/{id}")
    suspend fun sendReview(
        @Path("id") id: String = "6587bb435181e428b6d6568f", // Change this line
        @Body book: TeacherDTO
    ): retrofit2.Response<TeacherDTO>


    @GET("person/id/")
    suspend fun currentUser(
        @Query("personId") personId: String = "string",
    ): retrofit2.Response<PersonDTO>


}