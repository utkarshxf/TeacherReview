package com.orion.templete.Domain.use_case


import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.exploreDTO
import com.orion.templete.Domain.Repository.GetTherapistRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class GetTherapistUseCase @Inject constructor(private val getNewsArticleRepo: GetTherapistRepo) {
    operator fun invoke(): Flow<Resource<exploreDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(getNewsArticleRepo.getTherapist()))
        } catch (e: java.lang.Exception) {
            emit(Resource.Error(e.message))
        }
    }
}