package com.orion.templete.presentation.main.screens.setting

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.orion.templete.R
import com.orion.templete.presentation.ui.theme.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingScreen() {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.settings)) })
    }, content = {
        val modifier = Modifier.height(12.dp)
        SettinItems(modifier, it)
    })
}

@Composable
fun SettinItems(modifier: Modifier, paddingValues: PaddingValues) {
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val themeState = themeViewModel.themeStateHolder
    val notification = remember { mutableStateOf(false) }
    val isLanguageExtended = remember { mutableStateOf(false) }
    val languageOptions = listOf(
        stringResource(R.string.english), stringResource(R.string.hindi),
        stringResource(
            R.string.german
        )
    )
    val selectedLanguageOption = remember { mutableStateOf(languageOptions[0]) }
    Column(
        Modifier
            .padding(paddingValues)
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = modifier)
        TougleItems(
            notification,
            stringResource(R.string.notification),
            stringResource(R.string.get_notified_by_every_new_post_by_our_team)
        )
        Spacer(modifier = modifier)
        TougleItems(
            notification,
            stringResource(R.string.privacy),
            stringResource(R.string.show_my_name_in_reviews_section_of_app_profile)
        )
        Spacer(modifier = modifier)
        SettingCard(
            Name = stringResource(R.string.dark_mode),
            Dis = stringResource(R.string.change_theme_as_a_dark_theme)
        )
        Spacer(modifier = modifier)
        DynamicColourBar(
            Name = stringResource(R.string.dynamic_color),
            Dis = stringResource(R.string.you_can_generate_individualized_schemes_through_your_wallpaper_selected)
        )
        Spacer(modifier = modifier)
        LanguageChangeTab(
            isLanguageExtended,
            stringResource(R.string.change_app_language),
            languageOptions,
            selectedLanguageOption
        )
        Spacer(modifier = modifier)
        DirectLink(stringResource(R.string.term_and_conditions))
        Spacer(modifier = modifier)
        DirectLink(stringResource(R.string.private_policy))
        Spacer(modifier = modifier)
        DirectLink(stringResource(R.string.logout_my_account))
        Spacer(modifier = modifier)
        DirectLink(stringResource(R.string.delete_my_account))
        Spacer(modifier = modifier)
    }
}

@Composable
fun DirectLink(Name: String) {
    val modifier = Modifier
        .fillMaxWidth()
        .clickable {

        }
    Card(modifier = modifier) {
        Row(
            modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = Name,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LanguageChangeTab(
    expended: MutableState<Boolean>,
    Name: String,
    languageOptions: List<String>,
    selectedLanguageOption: MutableState<String>
) {
    val modifier = Modifier
        .fillMaxWidth()
    Card(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier.weight(1f)) {
                Text(text = Name, fontWeight = FontWeight.Bold)
                Text(text = selectedLanguageOption.value)
            }
            ExposedDropdownMenuBox(
                expanded = expended.value,
                onExpandedChange = {
                    expended.value = !expended.value
                }
            ) {
                Box(
                    Modifier
                        .width(100.dp)
                        .menuAnchor(), contentAlignment = Alignment.CenterEnd
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = null,
                    )
                }
                ExposedDropdownMenu(
                    expanded = expended.value,
                    onDismissRequest = { expended.value = false }
                ) {
                    languageOptions.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedLanguageOption.value = item
                                expended.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun DynamicColourBar(
    Name: String,
    Dis: String,
    themeViewModel: ThemeViewModel = hiltViewModel(),
    dynamicColor: State<Boolean> = themeViewModel.dynamicColor.collectAsState()
) {
    val modifier = Modifier
        .fillMaxWidth()
    Card(modifier = modifier) {
        Row(
            modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier.weight(1f)) {
                Text(text = Name, fontWeight = FontWeight.Bold)
                Text(text = Dis)
            }
            Switch(
                checked = dynamicColor.value,
                onCheckedChange = { themeViewModel.toggleDynamicColor() },
            )
        }
        Row(modifier = modifier.padding(12.dp)) {
            val selected = remember { mutableStateListOf(Int) }
            ThemeBox(MaterialTheme.colorScheme.primary)
            ThemeBox(MaterialTheme.colorScheme.secondary)
            ThemeBox(MaterialTheme.colorScheme.tertiary)
            ThemeBox(MaterialTheme.colorScheme.error)
            ThemeBox(MaterialTheme.colorScheme.background)
        }
    }
}

@Composable
fun ThemeBox(ThemeColour: Color) {
    Box(
        Modifier
            .padding(horizontal = 6.dp)
            .background(color = ThemeColour, shape = RoundedCornerShape(12.dp))
            .height(100.dp)
            .width(50.dp)
    )
}

@Composable
fun SettingCard(Name: String, Dis: String) {
    val modifier = Modifier.fillMaxWidth()
    val themeViewModel: ThemeViewModel = hiltViewModel()
    val themeState = themeViewModel.themeStateHolder.collectAsState()
    Card(modifier = modifier) {
        Row(
            modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier.weight(1f)) {
                Text(text = Name, fontWeight = FontWeight.Bold)
                Text(text = Dis)
            }
            Switch(
                checked = themeState.value.isDarkMode,
                onCheckedChange = { themeViewModel.toggleTheme() },
            )
        }
    }
}

@Composable
fun TougleItems(notification: MutableState<Boolean>, Name: String, Dis: String) {
    val modifier = Modifier.fillMaxWidth()
    Card(modifier = modifier) {
        Row(
            modifier.padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier.weight(1f)) {
                Text(text = Name, fontWeight = FontWeight.Bold)
                Text(text = Dis)
            }
            Switch(
                checked = notification.value,
                onCheckedChange = { notification.value = it },
            )
        }
    }
}


@Preview(showSystemUi = true, showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun T() {
    MaterialTheme {
        SettingScreen()
    }

}