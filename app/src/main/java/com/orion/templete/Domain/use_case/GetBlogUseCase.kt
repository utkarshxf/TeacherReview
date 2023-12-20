package com.orion.templete.Domain.use_case

import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Domain.Repository.GetBlogRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetBlogUseCase @Inject constructor(private val getBlogRepo: GetBlogRepo) {

    operator fun invoke(): Flow<Resource<BookListDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(getBlogRepo.getBooks()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

    operator fun invoke(key: String, updatedReviewBook: BooksDTO): Flow<Resource<BooksDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(getBlogRepo.update(key , updatedReviewBook)))
        } catch (e:Exception)
        {
            emit(Resource.Error(e.message))
        }
    }

}