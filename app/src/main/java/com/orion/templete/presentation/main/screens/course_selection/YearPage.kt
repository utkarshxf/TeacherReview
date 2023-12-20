package com.orion.templete.presentation.main.screens.course_selection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.orion.templete.Domain.use_case.Screen

@Composable
fun YearPage(navController: NavController) {
    Box(
        contentAlignment = Alignment.Center, modifier = Modifier
            .fillMaxSize()

    )
    {

        Column(modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()) {
            Text("Selection Year: " , fontSize = 32.sp)
            Card(modifier = Modifier.fillMaxWidth()) {
                OutlinedButton(modifier = Modifier.fillMaxWidth(), onClick = { navController.navigate(Screen.TopicsScreen.route)} , shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Year 1")
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(Screen.TopicsScreen.route) },shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Year 2")
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(Screen.TopicsScreen.route) }, shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Year 3")
                }
                Spacer(modifier = Modifier.height(12.dp))
                OutlinedButton(modifier = Modifier.fillMaxWidth(),onClick = { navController.navigate(Screen.TopicsScreen.route) }, shape = RoundedCornerShape(12.dp)) {
                    Text(text = "Year 4")
                }
            }
        }
    }
}
