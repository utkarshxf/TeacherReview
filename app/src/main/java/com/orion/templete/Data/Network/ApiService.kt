package com.orion.templete.Data.Network

import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Data.Model.PersonDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    after ? everything represent query
    @GET("books/")
    suspend fun getAllBooks(
    //we have to pass the query
    ):retrofit2.Response<BookListDTO>

    @GET("person/id/")
    suspend fun currentUser(
        @Query("personId") country:String = "123",
    ):retrofit2.Response<PersonDTO>
}