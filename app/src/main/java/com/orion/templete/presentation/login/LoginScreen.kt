package com.orion.templete.presentation.login

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.orion.templete.Domain.use_case.Screen
import com.orion.templete.R

@Composable
fun LoginScreen(navController: NavController) {
    val showSheet = remember { mutableStateOf(false) }

    if (showSheet.value) {
        BottomSheet {
            showSheet.value = false
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.backgroung),
            contentDescription = null,
            contentScale = ContentScale.Crop
        )
        TextButton(onClick = {
            navController.popBackStack()
            navController.navigate(Screen.MainScreen.route) }, modifier = Modifier.align(Alignment.TopEnd)) {
            Text(text = "Skip")
            Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription =null )
        }
        ProvideWindowInsets {
            login(showSheet, navController)
        }
    }
}

@Composable
fun login(showSheet: MutableState<Boolean>, navController: NavController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    val passwordFocusRequester = FocusRequester()
    val focusManager: FocusManager = LocalFocusManager.current

    Column(
        Modifier
            .navigationBarsWithImePadding()
            .padding(24.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Bottom),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), null,
            Modifier.size(80.dp),
            tint = Color.White
        )
        TextInput(InputType.Name, keyboardActions = KeyboardActions(onNext = {
            passwordFocusRequester.requestFocus()
        })) {
            username = it
        }
        TextInput(InputType.Password, keyboardActions = KeyboardActions(onDone = {
            focusManager.clearFocus()
            context.doLogin(username, password, navController)


        }), focsReqster = passwordFocusRequester) {
            password = it
        }
        Button(
            onClick = { context.doLogin(username, password, navController) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "SIGN IN", Modifier.padding(vertical = 8.dp))
        }

        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(horizontal = 12.dp)) {
            Divider(
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Text(
                text = stringResource(R.string.or),
                modifier = Modifier.padding(2.dp),
                color = Color.White.copy(alpha = 0.3f),
            )
            Divider(
                color = Color.White.copy(alpha = 0.3f),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
        Button(
            onClick = { showSheet.value = true }, modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.whatsapp),
                contentDescription = stringResource(R.string.whatsapp),
            )
            Text(text = "Login With Phone", color = MaterialTheme.colorScheme.onPrimary)
        }
        Divider(
            color = Color.White.copy(alpha = 0.3f),
            thickness = 1.dp,
            modifier = Modifier.padding(top = 48.dp)
        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Don't have an account ?", color = Color.White)
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Sign UP")
            }
        }
    }
}

sealed class InputType(
    val label: String,
    val icon: ImageVector,
    val keyboardOption: KeyboardOptions,
    val visualTransformation: VisualTransformation
) {
    object Name : InputType(
        label = "Email",
        icon = Icons.Default.Person,
        keyboardOption = KeyboardOptions(imeAction = ImeAction.Next),
        visualTransformation = VisualTransformation.None
    )

    object Password : InputType(
        label = "Password",
        icon = Icons.Default.Lock,
        keyboardOption = KeyboardOptions(imeAction = ImeAction.Done, keyboardType = KeyboardType.Password),
        visualTransformation = PasswordVisualTransformation()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    inputType: InputType,
    focsReqster: FocusRequester? = null,
    keyboardActions: KeyboardActions,
    onClickAction: (String) -> Unit
) {
    var value by remember { mutableStateOf("") }
    TextField(
        value = value,
        onValueChange = { value = it },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focsReqster ?: FocusRequester()),
        leadingIcon = { Icon(imageVector = inputType.icon, null) },
        label = { Text(text = inputType.label) },
        colors = TextFieldDefaults.colors(
//            focusedContainerColor = containerColor,
//            unfocusedContainerColor = containerColor,
//            disabledContainerColor = containerColor,
            //            backgroundColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        ),
        singleLine = true,
        keyboardOptions = inputType.keyboardOption,
        visualTransformation = inputType.visualTransformation,
        keyboardActions = keyboardActions,

        )
    onClickAction(value)

}

private fun Context.doLogin(email: String, password: String, navController: NavController) {
    Toast.makeText(this, email + password, Toast.LENGTH_LONG).show()


    when {
        email.isEmpty() -> {
            Toast.makeText(applicationContext, "user name is empty", Toast.LENGTH_SHORT).show()
        }

        password.isEmpty() -> {
            Toast.makeText(applicationContext, "password is empty", Toast.LENGTH_SHORT).show()
        }

        else -> {
            navController.navigate(Screen.MainScreen.route)
        }

    }
}

