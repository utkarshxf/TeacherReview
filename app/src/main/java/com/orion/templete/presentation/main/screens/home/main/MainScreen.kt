package com.orion.templete.presentation.main.screens.home.main

import CustomComponent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.main.componenets.LineChart

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(scrollBehavior: TopAppBarScrollBehavior , userViewModel: MainScreenModel = hiltViewModel())
{
    val res = userViewModel.User.value

    if (res.isLoading){
        Box(modifier = Modifier.fillMaxSize()){
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))

        }
    }
    if (res.error.isNotBlank()){
        Box(modifier = Modifier.fillMaxSize()){
            Text(text = res.error, modifier = Modifier.align(Alignment.Center))
        }
    }

    res.data?.let {
        RenderMain(scrollBehavior , res.data)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderMain(scrollBehavior: TopAppBarScrollBehavior, data: UserDTO?)
{
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
                Greeting(data.firstName+" "+data.lastName)
            }
            Spacer(modifier = Modifier.size(18.dp))
            if (data != null) {
                CircularIndicator(data.activeDays)
            }
            RoundedLinearProgressIndicator(progress = 0.4f)
            Spacer(modifier = Modifier.size(18.dp))
            Text(text = "Book Recommendation", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.size(18.dp))
            TherapistRow()
            Spacer(modifier = Modifier.size(18.dp))
            if (data != null) {
                LineChartScreen(data.dailyReport)
            }

            Spacer(modifier = Modifier.size(18.dp))
            Text(text = "Today’s Task", fontWeight = FontWeight.Bold)
            for (i in 0..2) {
                if (data != null) {
                    TodaysTask(R.drawable.cba, data.todayTask[i])
                }
            }
        }

    }
}

@Composable
fun TodaysTask(img: Int, s: String) {
    Spacer(modifier = Modifier.size(12.dp))
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp), // Adjust the height as needed
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface), // Set the card background color
        shape = RoundedCornerShape(12.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(id = img),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colorStops = arrayOf(
                                Pair(0.5f, Color.Transparent),
                                Pair(1.5f, MaterialTheme.colorScheme.primary)
                            )
                        )
                    )
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp), // Add padding to the Column for better text alignment
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    text = s,
                    fontSize = 18.sp, // Adjust the font size as needed
                    fontWeight = FontWeight.Bold,
                    color = Color.White // Set text color
                )
            }
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
    Text(text = "Over All Progress", fontWeight = FontWeight.Bold)
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
            progress = progress,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun LineChartScreen(dailyReport: List<Float>) {
    val dummyData = listOf(1f, 2f, 3f, 4f, 1f, 2f, 4f, 3f) // Replace with your actual data
    val gradient = Brush.horizontalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.secondary.copy(0.2f)
        )
    )
    val baseColor = MaterialTheme.colorScheme.primary
    Text(text = "Daily Report", fontWeight = FontWeight.Bold)
    Spacer(modifier = Modifier.size(24.dp))
    LineChart(
        values = dailyReport,
        gradient = gradient,
        baseColor = baseColor,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp) // Adjust the height as needed
    )
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
            smallText = stringResource(R.string.completed),
            indicatorValue = activeDays,
            maxIndicatorValue = 30,
            bigTextSuffix = stringResource(R.string.days)
        )
        CustomComponent(smallText = stringResource(R.string.today_s_task), indicatorValue = 85)
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
            Text(text = stringResource(R.string.reexamine))
        }
    }
}


@Composable
fun TherapistRow() {
    LazyRow {
        items(50) {
            Spacer(modifier = Modifier.width(12.dp))
            ProfileCard()
        }
    }
}
@Composable
fun ProfileCard() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(0.2f)), shape = RoundedCornerShape(12.dp))
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(6.dp)
            ).padding(6.dp),
    ) {
        Image(
            painter = painterResource(id = R.drawable.img), contentDescription = null, modifier = Modifier
                .padding(12.dp)
                .fillMaxSize()
            ,contentScale = ContentScale.Crop,
        )
        Text(text = "abed")
    }
}