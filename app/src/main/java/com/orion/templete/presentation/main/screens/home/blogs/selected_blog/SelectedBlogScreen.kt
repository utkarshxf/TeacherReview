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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.DarkGray
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.orion.templete.Data.Model.BookListDTO
import com.orion.templete.Data.Model.BooksDTO
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.main.MainScreenModel
import com.orion.templete.presentation.ui.theme.TempleteTheme
import kotlin.math.max
import kotlin.math.min

@Composable
fun SelectedBlogScreen(navigateToBlogs: () -> Unit = {}, addScreenData: BooksDTO?, goToReviewScreen: () -> Unit) {
    var scrollState = rememberLazyListState()
    Surface() {
        ContentText(scrollState = scrollState ,goToReviewScreen )
        ParallaxToolbar(scrollState = scrollState , navigateToBlogs = navigateToBlogs,addScreenData)
    }

}

@OptIn(ExperimentalGlideComposeApi::class)
@SuppressLint("SuspiciousIndentation")
@Composable
fun ParallaxToolbar(
    scrollState: LazyListState,
    navigateToBlogs: () -> Unit,
    addScreenData: BooksDTO?,
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
                    text = "Childhood Marketplace",
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Bold,
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
fun CircularButton(
    @DrawableRes iconResource: Int,
    color: Color = MaterialTheme.colorScheme.onSurface,
    elevation: ButtonElevation? = ButtonDefaults.buttonElevation(),
    onClick:()->Unit={}
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
        Icon(painterResource(id = iconResource), contentDescription = null , tint = MaterialTheme.colorScheme.outline)
    }
}


@Composable
fun BookInformation(goToReviewScreen: () -> Unit , userViewModel: MainScreenModel = hiltViewModel())
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

    res.data?.let { data->
        Spacer(modifier = Modifier.size(18.dp))
        AiIndicator(5)
        Text(text = "Reviews", fontWeight = Bold)
        Spacer(modifier = Modifier.size(18.dp))
        Reviews(goToReviewScreen)
        Spacer(modifier = Modifier.size(18.dp))
        Text(text = "Write your Review", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.size(18.dp))
        ReviewColoum()
    }
}
@Composable
fun AiIndicator(activeDays: Int) {
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
        CustomComponent(smallText = stringResource(R.string.liked_by), indicatorValue = 85)
    }
}

@Composable
fun Reviews(goToReviewScreen: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column() {

            Text("recipe.reviews", color = DarkGray)
        }
        Button(onClick = { goToReviewScreen() }, elevation = null, colors = ButtonDefaults.buttonColors(
            containerColor = Transparent,
        )) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = "See all")
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = null
                )


            }

        }
    }
}


@Composable
fun ReviewColoum() {
    Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp) , colors = CardDefaults.cardColors(containerColor = Transparent)) {
        Spacer(modifier = Modifier.size(18.dp))
        TextBox()
    }
}

@Composable
fun TextBox() {
    var text by rememberSaveable { mutableStateOf("") }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        singleLine = false,
        placeholder = { Text(color = MaterialTheme.colorScheme.outline,text = "Start typing here ...")},
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(horizontal = 12.dp),
    )
    Spacer(modifier = Modifier.height(12.dp))
    Box(modifier = Modifier.fillMaxWidth() , Alignment.Center ){
        Button(onClick = { /*TODO*/ } , shape = RoundedCornerShape(6.dp)) {
            Text(text = "Share Review")
        }
    }
    Spacer(modifier = Modifier.height(12.dp))
}

@Composable
fun ContentText(scrollState: LazyListState, goToReviewScreen: () -> Unit) {
    val AppBarExpendedHeight = 400.dp
    LazyColumn(
        contentPadding = PaddingValues(top = AppBarExpendedHeight),
        state = scrollState

    ) {
        item {
            Column(modifier = Modifier.padding(12.dp)){
                Text(text = "Lorem ipsum dolor sit amet consectetur. Volutpat nascetur turpis eget arcu. Vel volutpat porta cursus quis dignissim cursus commodo. Turpis congue mauris sed ut. Quis arcu fermentum duis a blandit eget lorem. Egestas metus turpis scelerisque eu. Ipsum eu morbi morbi lacus felis. Elementum suspendisse in massa tempor donec ultrices ultricies. Massa vitae sit est est tristique faucibus posuere quis. Ac sed ullamcorper urna ut.\n" +
                        "Eu nullam ornare donec leo dui augue dui. Viverra duis egestas eu pulvinar pharetra. Sed volutpat gravida vestibulum purus quis sed proin pulvinar. Quis ornare in auctor viverra sed elementum tellus diam. Est adipiscing magna tempor lacinia vitae. In viverra hendrerit suspendisse malesuada eu nec vitae amet. Neque tincidunt id rutrum aliquet volutpat donec placerat.\n")
                Spacer(modifier = Modifier.size(18.dp))
                BookInformation(goToReviewScreen)
            }

        }
    }
}


@Preview
@Composable
fun m() {
    TempleteTheme() {
//        SelectedBlogScreen(addScreenData = addScreenData)
    }
}