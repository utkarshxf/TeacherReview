package com.orion.templete.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.orion.templete.R
import com.orion.templete.Domain.use_case.Screen

@Composable
fun LoginScreen(navController: NavController) {
    val showSheet = remember { mutableStateOf(false) }

    if (showSheet.value) {
        BottomSheet() {
            showSheet.value = false
        }
    }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.surface)) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AppIcon()
            LoginSheet(navController,showSheet)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                verticalAlignment = Alignment.Bottom,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string._100_privacy),
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Divider(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .height(64.dp)
                        .width(1.dp)
                )
                Text(
                    text = "Top book of India",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Divider(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .height(64.dp)
                        .width(1.dp),
                )
                Text(
                    text = "Review App",
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
            }
        }
    }
}

@Composable
fun AppIcon() {
    Column(
        modifier = Modifier.height(LocalConfiguration.current.screenHeightDp.dp / 3),
        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.logo ),
            modifier = Modifier.fillMaxSize(),
            contentDescription = null
        )
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginSheet(navController: NavController, showSheet: MutableState<Boolean>) {

    Box(contentAlignment = Alignment.TopCenter) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 24.dp)
                .background(color = MaterialTheme.colorScheme.primaryContainer)
                .padding(top = LocalConfiguration.current.screenHeightDp.dp / 12)
                .padding(bottom = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextField(
                value = stringResource(R.string.phone_number), onValueChange = {}, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.surface,
                    unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onSurface,
                    unfocusedTextColor = MaterialTheme.colorScheme.onSurface
                ),
            )
            Spacer(modifier = Modifier.height(6.dp))
            Button(
                onClick = { navController.popBackStack()
                    navController.navigate(Screen.selectionCourse.route) }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp), shape = RoundedCornerShape(8.dp)

            ) {
                Text(text = stringResource(R.string.send_otp), color = MaterialTheme.colorScheme.onSecondary)
            }
            Spacer(modifier = Modifier.height(6.dp))
            Text(
                text = stringResource(R.string.by_signing_up_you_agree_to_our_terms_of_use_and_private_policy),
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                modifier = Modifier.padding(horizontal = 12.dp),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp)) {
                Divider(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
                Text(
                    text = stringResource(R.string.or),
                    modifier = Modifier.padding(2.dp),
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )
                Divider(
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                )
            }
            Spacer(modifier = Modifier.height(6.dp))
            Button(onClick = { showSheet.value = true }, modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)) {
                Image(
                    painter = painterResource(id = R.drawable.whatsapp),
                    contentDescription = stringResource(R.string.whatsapp),
                    modifier = Modifier.padding(end = 8.dp)
                )
                Spacer(modifier = Modifier.height(6.dp))
                Text(text = "Login With Moble",color = MaterialTheme.colorScheme.onPrimary)
            }
        }
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(8.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(vertical = 12.dp, horizontal = 12.dp),
            contentAlignment = Alignment.TopCenter
        ) {
            Text(
                text = "We have best reviews for books",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun Preview() {
//    TempleteTheme(darkTheme = true) {
//        LoginScreen()
//    }
//}