package com.orion.templete.Data.Network

import com.orion.templete.Data.Model.BlogDTO
import com.orion.templete.Data.Model.MintDTO
import com.orion.templete.Data.Model.UserDTO
import com.orion.templete.Data.Model.exploreDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
//    after ? everything represent query
    @GET("explore/")
    suspend fun getAllTherapist(
    //we have to pass the query
    ):retrofit2.Response<exploreDTO>

    @GET("person/id/")
    suspend fun currentUser(
        @Query("personId") country:String = "voodoo",
    ):retrofit2.Response<UserDTO>

    @GET("mints/")
    suspend fun getAllmints(
        //we have to pass the query
    ):retrofit2.Response<MintDTO>

    @GET("blogs/")
    suspend fun getAllblog(
        //we have to pass the query
    ):retrofit2.Response<BlogDTO>

}