package com.orion.templete.Domain.Di

import com.orion.templete.Data.Repository.GetTharapistImpl
import com.orion.templete.Data.Network.ApiService
import com.orion.templete.Data.Repository.GetBlogImpl
import com.orion.templete.Data.Repository.GetMintsImpl
import com.orion.templete.Data.Repository.GetUserImpl
import com.orion.templete.Domain.Repository.GetBlogRepo
import com.orion.templete.Domain.Repository.GetMintRepo
import com.orion.templete.Domain.Repository.GetTherapistRepo
import com.orion.templete.Domain.Repository.GetUserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {
    @Provides
    fun provideGetNewsRepo(apiService: ApiService): GetTherapistRepo {
        return GetTharapistImpl(apiService = apiService)
    }
    @Provides
    fun provideUserDetails(apiService: ApiService): GetUserRepo {
        return GetUserImpl(apiService = apiService)
    }

    @Provides
    fun provideBlogs(apiService: ApiService): GetBlogRepo {
        return GetBlogImpl(apiService = apiService)
    }

    @Provides
    fun provideMints(apiService: ApiService): GetMintRepo {
        return GetMintsImpl(apiService = apiService)
    }

}