package com.orion.templete.presentation.main.common.navigation_drawer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.orion.templete.R
import com.orion.templete.Domain.use_case.Screen
import com.orion.templete.presentation.ui.theme.ColorTextSecondary


@Composable
fun AppDrawer(
    route: String,
    closeDrawer: () -> Unit = {},
    navigateToMain: () -> Unit = {},
    navigateToProfile: () -> Unit = {},
    navigateToSettings: () -> Unit = {}
) {
    ModalDrawerSheet(modifier = Modifier.fillMaxHeight(), drawerShape = RoundedCornerShape(0.dp)) {
        DrawerHeader()
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(R.string.home),
                    style = MaterialTheme.typography.bodyMedium
                )
            },
            selected = route == Screen.MainScreen.route,
            onClick = {
                navigateToMain()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Home, contentDescription = null) },
            shape = RoundedCornerShape(0.dp)
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(R.string.profile), style = MaterialTheme.typography.bodyMedium) },
            selected = route == Screen.Profile.route,
            onClick = {
                navigateToProfile()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Person, contentDescription = null) },
            shape = RoundedCornerShape(0.dp)
        )
        NavigationDrawerItem(
            label = { Text(text = stringResource(R.string.settings), style = MaterialTheme.typography.bodyMedium) },
            selected = route == Screen.Settings.route,
            onClick = {
                navigateToSettings()
                closeDrawer()
            },
            icon = { Icon(imageVector = Icons.Default.Settings, contentDescription = null) },
            shape = RoundedCornerShape(0.dp)
        )
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
            Column() {
                Divider(
                    color = ColorTextSecondary,
                    thickness = 1.dp
                )
                Row(horizontalArrangement = Arrangement.SpaceBetween , verticalAlignment = Alignment.CenterVertically , modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
                        Icon(imageVector = Icons.Default.Person, contentDescription = null , tint = MaterialTheme.colorScheme.onSurface)
                        Spacer(modifier = Modifier.width(12.dp))
                        Column {
                            Text(text = stringResource(R.string.name) )
                            Text(text = stringResource(R.string.email), fontSize = 12.sp, color = ColorTextSecondary)
                        }
                    }
                    Icon(painter = painterResource(R.drawable.icon_logout), contentDescription = null, modifier = Modifier
                        .padding(end = 12.dp)
                        .clip(
                            RoundedCornerShape(100.dp)
                        )
                        .clickable {
                            //logout action
                        })
                }
            }

        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DrawerHeader() {
    Column {
        LargeTopAppBar(
            title = { Text(text = stringResource(id = R.string.app_name)) },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors()
        )
    }
}

@Preview
@Composable
fun DrawerHeaderPreview() {
    AppDrawer(Screen.MainScreen.route)
}