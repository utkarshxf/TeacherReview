package com.orion.templete.presentation.main.screens.home.topic_screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.orion.templete.Domain.use_case.Screen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopicScreen(
    nav: NavController
) {
//    var dummyData = generateDummyData()
//    Column {
//        mySearchBar()
//        LazyColumn {
//            items(dummyData) {
////                TopicsCard(it, nav)
//            }
//        }
//    }
}

@Composable
fun TopicsCard(
//    blogCardData: TopicDTOItem,
    nav: NavController,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(horizontal = 6.dp)
            .padding(vertical = 12.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(0.2f)), shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable { nav.navigate(Screen.MainScreen.route) },
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(Modifier)
            {
                Spacer(modifier = Modifier.height(12.dp))
            }

        }
    }
}