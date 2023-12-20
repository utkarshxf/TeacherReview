package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO

interface GetBlogRepo {

    suspend fun getBooks(): BookListDTO

    suspend fun update(key : String , updatedReviewBook : BooksDTO):BooksDTO

}