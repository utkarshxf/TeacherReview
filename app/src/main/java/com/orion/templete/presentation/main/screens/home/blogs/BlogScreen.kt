package com.orion.templete.presentation.main.screens.home.blogs


import android.content.res.Resources.Theme
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.orion.templete.Data.Model.BlogDTO
import com.orion.templete.Data.Model.BlogDTOItem
import com.orion.templete.R
import com.orion.templete.presentation.main.screens.home.mySearchBar
import com.orion.templete.presentation.main.screens.thought.common.PersonDetails

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BlogScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToSelectedBlog: (BlogDTOItem) -> Unit = {},
    blogViewModel: BlogScreenViewModel = hiltViewModel()
)
{
    val res = blogViewModel.Blogs.value

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
        RenderBlogScreen(scrollBehavior , res.data ,navigateToSelectedBlog)
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderBlogScreen(scrollBehavior: TopAppBarScrollBehavior, data: BlogDTO , navigateToSelectedBlog: (BlogDTOItem) -> Unit = {})
{
        Column(Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)) {
            mySearchBar()
            LazyColumn() {
                items(data) { blogCardData ->
                    BlogCard( blogCardData, navigateToSelectedBlog)
                }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun  BlogCard(
    blogCardData: BlogDTOItem,
    navigateToSelectedBlog: (BlogDTOItem) -> Unit,
) {
    Row(
        modifier = Modifier
            .height(250.dp)
            .padding(horizontal = 6.dp)
            .padding(vertical = 12.dp)
            .border(BorderStroke(1.dp, MaterialTheme.colorScheme.outline.copy(0.2f)), shape = RoundedCornerShape(12.dp))
            .clip(RoundedCornerShape(12.dp))
            .clickable { navigateToSelectedBlog(blogCardData) },
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 12.dp)
                .clip(RoundedCornerShape(6.dp))
                .height(200.dp)
                .width(150.dp)
        ) {
            // Use Coil/Glide library for efficient image loading
            GlideImage(
                model = blogCardData.imageUrl,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        Column(verticalArrangement = Arrangement.SpaceBetween , horizontalAlignment = Alignment.Start, modifier = Modifier.fillMaxHeight()) {
            Column(Modifier)
            {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = blogCardData.title,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 12.dp),
//                     MaterialTheme.typography.labelMedium
                )
                Text(
                    text = "alok randi",
                    modifier = Modifier.padding(horizontal = 12.dp), fontStyle = FontStyle.Italic
                )
                Rating(2)
            }


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_dot),
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "RS "+blogCardData.views.toString(),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.icon_like),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(28.dp)
                )
            }

        }
    }
}

@Composable
fun Rating(value:Int) {
    Row(horizontalArrangement = Arrangement.SpaceBetween ,
        verticalAlignment = Alignment.CenterVertically , modifier = Modifier.padding(12.dp))
    {
        for (i in 1..value) {
            Icon(painter = painterResource(id = R.drawable.ic_star), contentDescription = null , tint = MaterialTheme.colorScheme.primary , modifier = Modifier.size(28.dp))
        }
    }
}
