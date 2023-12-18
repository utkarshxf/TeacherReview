package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.BooksDTO

interface GetBlogRepo {

    suspend fun getBooks(): BooksDTO

}