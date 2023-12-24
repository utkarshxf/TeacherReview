package com.orion.templete.presentation.main.screens.home.my_review

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.orion.templete.R
import com.orion.templete.presentation.ui.theme.ColorTextSecondary
import com.orion.templete.presentation.ui.theme.TempleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyReview(scrollBehavior: TopAppBarScrollBehavior) {
    val modifier = Modifier.fillMaxSize()
    LazyColumn(
        content = {
            item {
                DailyPassCard()
            }
        },
        contentPadding = PaddingValues(12.dp),
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    )
}

@Composable
fun DailyPassCard() {
    Card(Modifier.fillMaxWidth()) {
        Column(
            Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Add, contentDescription = null,
                    Modifier
                        .clip(RoundedCornerShape(100.dp))
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp)
                )
                Text(text = "Daily Pass Membership")
            }
            FeatureText(
                R.drawable.icon_check,
                stringResource(R.string.enjoy_a_free_workflow_everyday)
            )
            FeatureText(
                R.drawable.icon_check,
                "one to one chat with experts"
            )
            FeatureText(
                R.drawable.icon_check, ""
            )
            Text(
                text = "More Details",
                color = ColorTextSecondary,
                modifier = Modifier.clickable { })
            Divider(modifier = Modifier.fillMaxWidth(), color = ColorTextSecondary)
            BuyButton(53)
        }
    }
}

@Composable
fun BuyButton(text: Int) {
    val annotatedString = buildAnnotatedString {
        append((text + 300).toString())
        addStyle(
            style = SpanStyle(
                textDecoration = TextDecoration.LineThrough,
                color = MaterialTheme.colorScheme.error
            ), start = 0, end = (text + 300).toString().length
        )
    }
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(text = annotatedString)
            Text(text = "$text/10 Days")
        }
        Button(onClick = { }, shape = RoundedCornerShape(6.dp)) {
            Text(text = "Buy Now")
        }
    }
}

@Composable
fun FeatureText(iconCheck: Int, text: String) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(painter = painterResource(id = iconCheck), contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))
        Text(text = text)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    widthDp = 380,
    heightDp = 2000
)
@Composable
fun M() {
    TempleteTheme {
        val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
        MyReview(scrollBehavior)
    }
}