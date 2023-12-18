package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.BookListDTO

interface GetBlogRepo {

    suspend fun getBooks(): BookListDTO

}