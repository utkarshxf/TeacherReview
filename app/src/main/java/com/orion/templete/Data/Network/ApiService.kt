package com.orion.templete.Data.Network

import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Data.Model.PersonDTO
import com.orion.templete.Data.Model.TopicDTOItem
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Query

interface ApiService {
//    after ? everything represent query
    @GET("books/")
    suspend fun getAllBooks(
    //we have to pass the query
    ):retrofit2.Response<BookListDTO>
    @PUT("books/")
    suspend fun sendReview(
        @Body book: BooksDTO,
        @Query("key") key: String = "65816794cee17469e5886f14",
    ): retrofit2.Response<BooksDTO>

    @GET("person/id/")
    suspend fun currentUser(
        @Query("personId") personId:String = "abcd",
    ):retrofit2.Response<PersonDTO>

    @GET("topics/")
    suspend fun getAllTopics(
        //we have to pass the query
    ):retrofit2.Response<TopicDTOItem>

}