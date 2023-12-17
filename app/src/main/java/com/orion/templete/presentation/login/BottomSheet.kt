package com.orion.templete.presentation.login

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orion.templete.R
import com.orion.templete.presentation.ui.theme.ColorTextSecondary
import com.orion.templete.presentation.ui.theme.TempleteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(onDismiss: () -> Unit) {
    val modalBottomSheetState = rememberModalBottomSheetState()

    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = modalBottomSheetState,
        dragHandle = { BottomSheetDefaults.DragHandle() },
    ) {
       PopOut()
    }
}

@Composable
fun PopOut() {
    Column(modifier = Modifier.padding(12.dp)) {
        Discription(stringResource(R.string.hi_utkarsh_rajput))
        Spacer(modifier = Modifier.height(12.dp))
        ProceedButton(PhoneNo = 9009718343)
        Spacer(modifier = Modifier.height(12.dp))
        Divider(modifier = Modifier.fillMaxWidth(), color = ColorTextSecondary)
        Spacer(modifier = Modifier.height(12.dp))
        Details()
    }
}

@Composable
fun Discription(Name:String) {
    Text(text =Name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
    Text(text = stringResource(R.string.to_get_started_please_login_signup) )
}

@Composable
fun ProceedButton(PhoneNo: Long) {
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
            Text(text = stringResource(R.string.proceed_with, PhoneNo),modifier = Modifier.padding(6.dp),fontWeight = FontWeight.Bold)
        }
        Button(onClick = { /*TODO*/ },modifier = Modifier.fillMaxWidth(),shape = RoundedCornerShape(8.dp), colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent, contentColor = ColorTextSecondary)) {
            Text(text = stringResource(R.string.use_other_method), modifier = Modifier.padding(6.dp))
        }
    }
}

@Composable
fun Details() {
    Text(text = stringResource(R.string.by_continuing_you_consent_to_share_your_name_and_number_with_welltak_and_agree_to_welltalk_privacy_policy_and_terms_of_service),fontSize = 8.sp, color = ColorTextSecondary)
}

@Preview
@Composable
fun ModernState() {
    TempleteTheme() {
        BottomSheet {

        }
    }
}