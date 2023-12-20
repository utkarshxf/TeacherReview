package com.orion.templete.Domain.Repository

import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.Data.Model.TopicDTO
import com.orion.templete.Data.Model.TopicDTOItem

interface GetTopicRepo {

    suspend fun getTopic(): TopicDTOItem
}