package com.orion.templete.presentation.main.screens.home.chat

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.presentation.ui.theme.TempleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    scrollBehavior: TopAppBarScrollBehavior,
    chatViewModel: ChatViewModel = hiltViewModel()
) {
    val res = chatViewModel.therapistList.value

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
        RenderChatScreen(scrollBehavior, res.data)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenderChatScreen(scrollBehavior: TopAppBarScrollBehavior, data: exploreDTO) {
    val lazyListState = rememberLazyListState()
    val modifier = Modifier.fillMaxWidth()
    LazyColumn(
        state = lazyListState,
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(12.dp),
        reverseLayout = false,
        horizontalAlignment = Alignment.Start
    ) {
        for (i in 0 until data.size) {
            item {
                TherapistCard(modifier, data[i])
            }
        }
    }
}


@Composable
fun TherapistCard(modifier: Modifier, exploreDTOItem: exploreDTOItem) {
    Card(
        shape = RoundedCornerShape(6.dp),
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .padding(12.dp)
        ) {
            Row {
                TherapistAvatar()
                FieldText(exploreDTOItem)
            }
            ChatButton(exploreDTOItem)
        }
    }
}

@Composable
fun TherapistAvatar() {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(124.dp)) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = null,
            modifier = Modifier.size(64.dp)
        )
        Row {
            repeat(4) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_star),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primaryContainer
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.icon_half_star),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primaryContainer
            )
        }
    }
}

@Composable
fun FieldText(exploreDTOItem: exploreDTOItem) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(text = exploreDTOItem.name)
        Text(
            text = convt(exploreDTOItem.experiences),
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = "Exp: " + exploreDTOItem.exp + " years",
            style = MaterialTheme.typography.bodySmall
        )
        Text(
            text = exploreDTOItem.fees.toString() + "/hr",
            style = MaterialTheme.typography.bodySmall
        )
    }

}

fun convt(experiences: List<String>): String {
    var st: String = ""
    for (i in experiences.indices) {
        st += i
    }
    return st
}

@Composable
fun ChatButton(exploreDTOItem: exploreDTOItem) {
    val modifier = Modifier
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(18.dp),
        modifier = modifier.fillMaxHeight()
    ) {
        Icon(
            painter = painterResource(R.drawable.icon_check),
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier
                .background(
                    color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                    shape = RoundedCornerShape(100.dp)
                )
                .padding(6.dp)
        )
        Button(
            onClick = { /* TODO: Implement chat button click */ },
            shape = RoundedCornerShape(6.dp)
        ) {
            Text(text = stringResource(R.string.connect))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, widthDp = 380, heightDp = 2000
)
@Composable
fun M() {
    TempleteTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        ChatScreen(scrollBehavior)
    }
}
