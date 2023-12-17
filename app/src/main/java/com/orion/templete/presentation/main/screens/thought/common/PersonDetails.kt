package com.orion.templete.presentation.main.screens.thought.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun PersonDetails(modifier: Modifier, Name: String, Gmail: String, ProfileImage: Int) {
    Row(
        modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(painterResource(id = ProfileImage), contentDescription = null)
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier.weight(1f)) {
            Text(text = Name, fontWeight = FontWeight.Bold)
            Text(text = Gmail)
        }
    }
}