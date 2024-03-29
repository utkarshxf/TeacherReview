package com.orion.templete.presentation.main.screens.home.teachers_list.selected_teacher.add_review

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.Data.Model.Review
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.teachers_list.TeachersListScreenViewModel
import com.orion.templete.presentation.ui.theme.TempleteTheme
import java.util.Date


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddReviewScreen(data: TeacherDTO?) {
    Scaffold(topBar = { CenterAlignedTopAppBar(title = {  Text(text = data!!.name)}) }) {
        Column(Modifier.padding(it).padding(horizontal = 12.dp)) {
            ReviewColoum(data)
        }

    }
}

@Composable
fun ReviewColoum(bookData: TeacherDTO?) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
    ) {
        item {
            Spacer(modifier = Modifier.size(18.dp))
            ReviewSection(bookData)
        }
    }
}

@Composable
fun ReviewSection(bookData: TeacherDTO?, bookViewModel: TeachersListScreenViewModel = hiltViewModel()) {
    val context = LocalContext.current
    var text by rememberSaveable { mutableStateOf("") }
    var teachingRate by remember { mutableStateOf(0) }
    var internalMarksRate by remember { mutableStateOf(0) }
    var externalMarksRate by remember { mutableStateOf(0) }
    val res = bookViewModel.TeacherList.value
    val TempReview = Review(teachingStyle = teachingRate , internalMarks = internalMarksRate , externalMark = externalMarksRate , reviewText = text, userId = "null", verified = false , date = Date().toString() , showText = false , anonymous = false)
    Text(text = "Teaching Style", fontWeight = FontWeight.Bold)
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
                    tint = if (index < teachingRate) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            teachingRate = index + 1
                        }
                )
            }
        }
    }
    Text(text = "Internal Marks", fontWeight = FontWeight.Bold)
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
                    tint = if (index < internalMarksRate) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            internalMarksRate = index + 1
                        }
                )
            }
        }
    }
    Text(text = "End/Mid Exams Marking", fontWeight = FontWeight.Bold)
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
                    tint = if (index < externalMarksRate) MaterialTheme.colorScheme.primary else Color.Gray,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable {
                            externalMarksRate = index + 1
                        }
                )
            }
        }
    }
    Text(text = "Write Review", fontWeight = FontWeight.Bold)
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
    Box(modifier = Modifier.fillMaxWidth(), Alignment.Center) {
        Button(onClick = {
            bookData?.review = bookData?.review.orEmpty() + TempReview
            if (bookData != null) {
                bookViewModel.updateBooks(bookData._id, bookData)
            }
            Log.v("qwerty",res.toString())
            res.data.let {
                Toast.makeText(context,"Reviews will be reviewed by our review team\uD83E\uDEE3", Toast.LENGTH_SHORT).show()
                text = ""
                teachingRate = 0
                internalMarksRate=0
                externalMarksRate =0
            }
            res.error.let {
                Toast.makeText(context,it, Toast.LENGTH_SHORT).show()
            }

        }, shape = RoundedCornerShape(6.dp)) {
            Text(text = "Share Review")
        }
    }
    Spacer(modifier = Modifier.height(12.dp))

}
@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun M() {
    TempleteTheme {
//        MintsScreen(scrollBehavior)
    }
}