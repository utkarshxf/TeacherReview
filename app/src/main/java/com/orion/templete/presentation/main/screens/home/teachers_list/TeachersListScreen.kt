package com.orion.templete.presentation.main.screens.home.teachers_list


import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.orion.templete.Data.Model.TeacherDTO
import com.orion.templete.Data.Model.TeacherListDTO
import com.orion.templete.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeacherListScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    navigateToSelectedBlog: (TeacherDTO) -> Unit = {},
    blogViewModel: TeachersListScreenViewModel = hiltViewModel()
) {
    val res = blogViewModel.TeacherList.value
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

    res.data?.let {
        RenderBlogScreen(scrollBehavior, res.data, navigateToSelectedBlog)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderBlogScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    data: TeacherListDTO,
    navigateToSelectedTeacher: (TeacherDTO) -> Unit = {}
) {
    Column(
        Modifier
            .nestedScroll(scrollBehavior.nestedScrollConnection)
    ) {
        MySearchBar(navigateToSelectedTeacher)
        LazyColumn {
            items(data) { blogCardData ->
                BlogCard(blogCardData, navigateToSelectedTeacher)
            }
        }
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun BlogCard(
    blogCardData: TeacherDTO,
    navigateToSelectedTeacher: (TeacherDTO) -> Unit,
    blogViewModel: TeachersListScreenViewModel = hiltViewModel()
) {
    Row(
        modifier = Modifier
            .height(200.dp)
            .padding(horizontal = 6.dp)
            .padding(vertical = 12.dp)
            .border(
                BorderStroke(1.dp, MaterialTheme.colorScheme.onSurface.copy(0.2f)),
                shape = RoundedCornerShape(6.dp)
            )
            .clip(RoundedCornerShape(12.dp))
            .clickable { navigateToSelectedTeacher(blogCardData) },
    ) {
        Box(
            modifier = Modifier
                .padding(12.dp)
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
        Column(
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(Modifier)
            {
                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = blogCardData.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(horizontal = 12.dp),
//                     MaterialTheme.typography.labelMedium
                )
                Text(
                    text = blogCardData.about,
                    maxLines = 2,
                    style = MaterialTheme.typography.bodyMedium,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 12.dp), fontStyle = FontStyle.Italic
                )
                val ratingValue = blogViewModel.calculateOverallStarRating(blogCardData.review)
                Rating(ratingValue)
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
                        text = blogCardData.subject,
                        style = MaterialTheme.typography.labelMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.share_2),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.size(18.dp)
                )
            }

        }
    }
}

@Composable
fun Rating(value: Int) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(12.dp)
    )
    {
        repeat(value) {
            Icon(
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(2.dp)
                    .size(24.dp)
            )
        }
    }
}
