package com.orion.templete.presentation.main.screens.home.profile

import CustomComponent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.orion.templete.Data.Model.PersonDTO
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.teachers_list.TeachersListScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(scrollBehavior: TopAppBarScrollBehavior, userViewModel: ProfileScreenModel = hiltViewModel()) {
    val res = userViewModel.User.value

    if (res.isLoading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }
    }
    if (res.error.isNotBlank()) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }

    RenderMain(scrollBehavior, res.data)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderMain(scrollBehavior: TopAppBarScrollBehavior, data: PersonDTO?) {
    val state = rememberLazyListState()
    LazyColumn(
        modifier = Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
            .padding(horizontal = 12.dp),
        contentPadding = PaddingValues(0.dp),
        horizontalAlignment = Alignment.Start,
        state = state,
    ) {
        item {
            Spacer(modifier = Modifier.size(18.dp))
            if (data != null) {
                Greeting(data.firstName + " " + data.lastName)
            }
            Spacer(modifier = Modifier.size(18.dp))
            if (data != null) {
                CircularIndicator(5)
            }
            RoundedLinearProgressIndicator(progress = 4f)
            Spacer(modifier = Modifier.size(18.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Teacher Recommendation", fontWeight = FontWeight.Bold)
                Card(
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                ) {
                    Text(
                        text = "more",
                        color = MaterialTheme.colorScheme.outline.copy(0.8f),
                        modifier = Modifier.clickable {

                        }
                    )
                }
            }

            Spacer(modifier = Modifier.size(18.dp))
            TeacherRecommendation()
        }

    }
}


@Composable
fun RoundedLinearProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    shape: Shape = RoundedCornerShape(50) // Adjust the corner radius as needed
) {
    Text(text = "Level : "+progress.toInt(), fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.size(12.dp))
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(16.dp),
        shape = shape,
        color = color
    ) {
        LinearProgressIndicator(
            trackColor = MaterialTheme.colorScheme.background.copy(0.25f),
            progress = 0.1f * progress,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun CircularIndicator(activeDays: Int) {
    Text(text = stringResource(R.string.activity), fontWeight = FontWeight.Bold)
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomComponent(
            smallText = "Total Reviews",
            indicatorValue = activeDays,
            maxIndicatorValue = 30,
            bigTextSuffix = ""
        )
        CustomComponent(smallText = "Completed Profile", indicatorValue = 85)
    }
}

@Composable
fun Greeting(name: String) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(R.drawable.avatar),
                contentDescription = null,
                Modifier.size(80.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column {
                Text(text = stringResource(R.string.good_afternoon), style = MaterialTheme.typography.titleMedium)
                Text(text = name, style = MaterialTheme.typography.titleLarge)
            }
        }
        Button(onClick = { /*TODO*/ }, shape = RoundedCornerShape(12.dp)) {
            Text(text = "Edit Profile")
        }
    }
}


@Composable
fun TeacherRecommendation() {
    val res :TeachersListScreenViewModel = hiltViewModel()
    res.TeacherList.value.data?.let {
        LazyRow {
            items(it) {
                Spacer(modifier = Modifier.width(12.dp))
                TeacherRCard(it)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TeacherRCard(i: TeacherDTO) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.size(width = 150.dp , height = 230.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(0.2f)), shape = RoundedCornerShape(12.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(6.dp),
    ) {
        GlideImage(
            model = i.imageUrl, contentDescription = null,
            modifier = Modifier
                .padding(12.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop,
        )
        Text(text = i.name)
    }
}