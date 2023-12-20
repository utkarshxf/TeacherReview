package com.orion.templete.presentation.main.screens.home.topic_screen

import com.orion.templete.Data.Model.TopicDTO
import com.orion.templete.Data.Model.TopicDTOItem

fun generateDummyData(): TopicDTO {
    val dummyTopics = TopicDTO().apply {
        add(TopicDTOItem(_id = "1", about = "Description 1", name = "Topic 1"))
        add(TopicDTOItem(_id = "2", about = "Description 2", name = "Topic 2"))
        add(TopicDTOItem(_id = "2", about = "Description 2", name = "Topic 2"))

        // Add more topics as needed
    }

    return dummyTopics
}