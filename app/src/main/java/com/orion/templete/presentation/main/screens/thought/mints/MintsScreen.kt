package com.orion.templete.presentation.main.screens.thought.mints

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.thought.common.PersonDetails
import com.orion.templete.presentation.ui.theme.TempleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MintsScreen(mintsViewModel: MintsScreenViewModel = hiltViewModel())
{
    val res = mintsViewModel.Mints.value

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
        RenderMintsScreen( res.data)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderMintsScreen(data: Any)
{
    val modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)
        .height(12.dp)
    LazyColumn(modifier) {
        item() {
            Text("you can write your own quotes here", textAlign = TextAlign.Center)
        }
        items(1000) {
            MintsCard("Olivia Rhye", "qlivia332", R.drawable.avatar, 32)
            Spacer(modifier = modifier)
        }
    }
}

@Composable
fun MintsCard(Name: String, Gmail: String, ProfileImage: Int, Views: Int) {
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 12.dp)
    Card() {
        Spacer(modifier = modifier.height(12.dp))
        PersonDetails(modifier, Name, Gmail, ProfileImage)
        Spacer(modifier = modifier.height(12.dp))
        Text(
            text = "Lorem ipsum dolor sit amet consectetur. Semper eros volutpat pretium semper urna cras est. Purus et diam elementum ut. Purus viverra non nec amet volutpat penatibus dui.",
            modifier = modifier
        )
        Spacer(modifier = modifier.height(12.dp))

    }
}



@Preview(showSystemUi = true, showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun M() {
    TempleteTheme() {
//        MintsScreen(scrollBehavior)
    }
}