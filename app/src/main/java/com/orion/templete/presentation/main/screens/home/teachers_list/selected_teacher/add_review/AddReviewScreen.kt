package com.orion.templete.presentation.main.screens.home.teachers_list.selected_teacher.add_review

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.Data.Model.Review
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.teachers_list.TeachersListScreenViewModel
import com.orion.templete.presentation.main.screens.thought.common.PersonDetails
import com.orion.templete.presentation.ui.theme.TempleteTheme


@Composable
fun AddReviewScreen(data: TeacherDTO?) {
    Column {
        Text(text = "Write Your Review", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(18.dp))
        ReviewColoum(data)
        Spacer(modifier = Modifier.size(32.dp))
    }
}

@Composable
fun ReviewColoum(bookData: TeacherDTO?) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.Transparent)
    ) {
        Spacer(modifier = Modifier.size(18.dp))
        ReviewSection(bookData)
    }
}

@Composable
fun ReviewSection(bookData: TeacherDTO?, bookViewModel: TeachersListScreenViewModel = hiltViewModel()) {
    var text by rememberSaveable { mutableStateOf("") }
    var selectedStars by remember { mutableStateOf(0) }
    val res = bookViewModel.TeacherList.value
    val TempReview = Review("null", "null", selectedStars, text, "null", false)
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = false,
        placeholder = { Text(color = MaterialTheme.colorScheme.outline, text = "Start typing here ...") },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 12.dp),
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(text = "Give Your Rating", fontWeight = FontWeight.Bold)

    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(12.dp)
        ) {
            repeat(5) { index ->
                Icon(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    tint = if (index < selectedStars) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(45.dp)
                        .clickable {
                            selectedStars = index + 1
                        }
                )
            }
        }
    }
    Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
        Button(onClick = {
            bookData?.review = bookData?.review.orEmpty() + TempReview
            if (bookData != null) {
                bookViewModel.updateBooks("string", bookData)
            }
            res.let {
                Log.v("qwerty", it.toString())
                text = ""
                selectedStars = 0
            }

        }, shape = RoundedCornerShape(6.dp)) {
            Text(text = "Share My Review")
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}


@Composable
fun ReviewCard(review: Review) {
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