package com.orion.templete.Domain.Di

import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Data.Repository.TeacherImpl
import com.orion.templete.Data.Repository.GetUserImpl
import com.orion.templete.Domain.Repository.TeacherRepo
import com.orion.templete.Domain.Repository.GetUserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideUserDetails(apiService: ApiService): GetUserRepo {
        return GetUserImpl(apiService = apiService)
    }

    @Provides
    fun provideTeacherRepo(apiService: ApiService): TeacherRepo {
        return TeacherImpl(apiService = apiService)
    }

}