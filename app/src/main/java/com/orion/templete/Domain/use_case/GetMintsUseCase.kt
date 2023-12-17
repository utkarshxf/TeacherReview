package com.orion.templete.Domain.use_case

import com.orion.newsapp.util.Resource
import com.orion.templete.Data.Model.MintDTO
import com.orion.templete.Domain.Repository.GetMintRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetMintsUseCase @Inject constructor(private val getMintRepo: GetMintRepo) {

    operator fun invoke(): Flow<Resource<MintDTO>> = flow {
        emit(Resource.Loading(""))
        try {
            emit(Resource.Success(getMintRepo.getMints()))
        } catch (e: Exception) {
            emit(Resource.Error(e.message))
        }
    }

}