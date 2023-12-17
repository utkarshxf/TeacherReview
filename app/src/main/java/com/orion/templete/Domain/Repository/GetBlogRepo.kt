package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.BlogDTO
import com.orion.templete.Data.Model.exploreDTO

interface GetBlogRepo {

    suspend fun getBlogs(): BlogDTO

}