import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.orion.templete.Data.Model.Review
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.teachers_list.TeachersListScreenViewModel
import com.orion.templete.presentation.main.screens.thought.common.PersonDetails
import com.orion.templete.presentation.ui.theme.TempleteTheme
import kotlin.math.max
import kotlin.math.min

@Composable
fun SelectedBlogScreen(
    navigateToBlogs: () -> Unit = {},
    addScreenData: TeacherDTO?,
    goToReviewScreen: (TeacherDTO?) -> Unit
) {
    var scrollState = rememberLazyListState()
    Surface {
        ContentText(scrollState = scrollState, addScreenData)
        Box(modifier = Modifier.fillMaxSize())
        {
            ParallaxToolbar(scrollState = scrollState, navigateToBlogs = navigateToBlogs, addScreenData)
            Button(
                onClick = { goToReviewScreen(addScreenData) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(12.dp)
            ) {
                Text(text = "Add My Review")
            }
        }
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
    navigateToBlogs: () -> Unit,
    addScreenData: TeacherDTO?,
) {
    val AppBarCollapsedHeight = 56.dp
    val AppBarExpendedHeight = 400.dp
    val imageHight = AppBarExpendedHeight - AppBarCollapsedHeight
    val maxOffset = with(LocalDensity.current) {
        imageHight.roundToPx()
    } - WindowInsets.systemBars.getTop(LocalDensity.current)
    val offset = min(scrollState.firstVisibleItemScrollOffset, maxOffset)
    val offsetprogress = max(0f, offset * 3f - 2f * maxOffset) / maxOffset
    val imageHeight = AppBarExpendedHeight - AppBarCollapsedHeight
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = Modifier
            .height(AppBarExpendedHeight)
            .offset { IntOffset(x = 0, y = -offset) },
        elevation = CardDefaults.cardElevation(defaultElevation = if (offset == maxOffset) 4.dp else 0.dp),
        shape = RoundedCornerShape(0.dp)
    ) {
        Column {
            Box(
                Modifier
                    .height(imageHeight)
                    .graphicsLayer {
                        alpha = 1f - offsetprogress
                    }) {
                GlideImage(
                    addScreenData?.imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxHeight()
                )
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(
                            Brush.verticalGradient(
                                colorStops = arrayOf(
                                    Pair(
                                        0.4f,
                                        Transparent
                                    ),
                                    Pair(1f, MaterialTheme.colorScheme.surface)
                                )
                            )
                        )
                )
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .height(AppBarCollapsedHeight),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = addScreenData!!.name,
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier
                        .padding(horizontal = (16 + 28 * offsetprogress).dp)
                        .scale(1f - 0.25f * offsetprogress)
                )
            }
        }
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .height(AppBarCollapsedHeight)
            .padding(horizontal = 16.dp)
    ) {
        CircularButton(R.drawable.ic_arrow_back, onClick = {
            navigateToBlogs()
        })
        CircularButton(R.drawable.ic_favorite)
    }
}

@Composable
fun TeacherTeachingProgressIndicator(
    progress: Float,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.1f),
    shape: Shape = RoundedCornerShape(50) // Adjust the corner radius as needed
) {
    var colorProgress = progress.toInt()
    Text(text = "Teaching : "+ when {
        colorProgress < 4 -> "Poor"
        colorProgress < 8 -> "Good"
        else -> "Excellent"
    }
        , fontWeight = Bold)
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
            modifier = Modifier.fillMaxSize(),
            color = when {
                colorProgress < 3 -> MaterialTheme.colorScheme.error
                else -> MaterialTheme.colorScheme.primary
            }
        )
    }
}

@Composable
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = MaterialTheme.colorScheme.onSurface,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    onClick: () -> Unit = {}
) {

    Button(
        onClick = onClick,
        contentPadding = PaddingValues(),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = White, contentColor = color),
        elevation = elevation,
        modifier = Modifier
            .width(38.dp)
            .height(38.dp)

    ) {
        Icon(painterResource(id = iconResource), contentDescription = null, tint = MaterialTheme.colorScheme.outline)
    }
}

@Composable
fun ContentText(scrollState: LazyListState, addScreenData: TeacherDTO?) {
    val AppBarExpendedHeight = 400.dp
    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight),
        state = scrollState

    ) {
        item {
            Column(modifier = Modifier.padding(12.dp)) {
                if (addScreenData != null) {
                    Text(text = addScreenData.about, style = MaterialTheme.typography.bodyMedium)
                } else {
                    Text(text = "nothing about is present")
                }
                Spacer(modifier = Modifier.size(18.dp))
                BookInformation(addScreenData)
            }

        }
    }
}


@Composable
fun BookInformation(bookData: TeacherDTO?,viewModel: TeachersListScreenViewModel = hiltViewModel()) {
    val averageRating = viewModel.calculateAvarageTeaching(bookData!!.review)*2.0
    val averageExternalMarks = viewModel.calculateAverageExternalMarks(bookData.review)*2
    val averageInternalMarks = viewModel.calculateAverageInternalMarks(bookData.review)*2
    Spacer(modifier = Modifier.size(18.dp))
    Indicator(averageExternalMarks.toInt(), averageInternalMarks.toInt())
    TeacherTeachingProgressIndicator(averageRating.toFloat())
    Spacer(modifier = Modifier.size(18.dp))
    Text(text = "Reviews", fontWeight = Bold)
    Spacer(modifier = Modifier.size(18.dp))
    AllReview(bookData!!.review)
    Spacer(modifier = Modifier.size(32.dp))
}

@Composable
fun Indicator(external: Int, internal: Int) {
    Text(text = stringResource(R.string.activity), fontWeight = Bold)
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        CustomComponent(
            smallText = "External",
            indicatorValue = external,
            maxIndicatorValue = 10,
            bigTextSuffix = when {
                external < 4 -> "Poor"
                external < 8 -> "Good"
                else -> "Excellent"
            },
            foregroundIndicatorColor = when {
                external > 3 -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.error
            }
        )
        CustomComponent(
            smallText = "Internal",
            maxIndicatorValue = 10,
            indicatorValue = internal,
            bigTextSuffix = when {
                internal < 4 -> "Poor"
                internal < 8 -> "Good"
                else -> "Excellent"
            },
            foregroundIndicatorColor = when {
                internal > 3 -> MaterialTheme.colorScheme.primary
                else -> MaterialTheme.colorScheme.error
            }
        )
    }
}

@Composable
fun AllReview(data: List<Review>) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .height(500.dp)
    ) {
        items(data) {
            if (it.reviewText.isNotEmpty()) {
                ReviewCard(it)
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }

}

@Composable
fun ReviewCard(review: Review) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
    Card {
        Spacer(modifier = modifier.height(12.dp))
        PersonDetails(modifier, review.userId, review.date.take(10), R.drawable.avatar)
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = review.reviewText,
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))
    }
}

@Preview
@Composable
fun m() {
    TempleteTheme {
//        SelectedBlogScreen(addScreenData = addScreenData)
    }
}