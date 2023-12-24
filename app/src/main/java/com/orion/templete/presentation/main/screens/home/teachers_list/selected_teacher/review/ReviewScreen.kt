package com.orion.templete.presentation.main.screens.home.teachers_list.selected_teacher.review

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orion.templete.Data.Model.Review
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.thought.common.PersonDetails
import com.orion.templete.presentation.ui.theme.TempleteTheme


@Composable
fun MintsScreen(data: List<Review>) {
    val modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)
        .height(12.dp)
    LazyColumn(modifier) {
        items(data) {
            MintsCard(it)
            Spacer(modifier = modifier)
        }
    }

}

@Composable
fun MintsCard(review: Review) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
    Card {
        Spacer(modifier = modifier.height(12.dp))
        PersonDetails(modifier, review.userId, review.bookId, R.drawable.avatar)
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = review.reviewText,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))

    }
}


@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun M() {
    TempleteTheme {
//        MintsScreen(scrollBehavior)
    }
}