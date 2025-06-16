package com.robosoft.androidtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.Button
import androidx.tv.material3.DrawerValue
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.NavigationDrawer
import androidx.tv.material3.NavigationDrawerItem
import androidx.tv.material3.Surface
import androidx.tv.material3.Text
import com.robosoft.androidtv.ui.theme.AndroidTVDemoTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidTVDemoTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.Red), shape = RectangleShape
                ) {
                    AppNavHost()
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {
    Text(
        text = "Home Screen", modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun SettingsScreen(goBack: () -> Unit) {
    Column {
        Text("Settings")
        Button(onClick = {
            goBack()
        }) {
            Text(text = "Click Me")
        }
    }
}

@Composable
fun SearchScreen() {
    Text(
        text = "Search Screen", modifier = Modifier.fillMaxSize()
    )
}

@Composable
fun AppNavHost() {
    val navHostController = rememberNavController()

    NavHost(
        navController = navHostController, startDestination = NavScreen.Home.route
    ) {
        composable(
            route = NavScreen.Home.route
        ) {
            AppNavDrawer(
                currentRoute = navHostController
            ) {
                HomeScreen()
            }
        }
        composable(
            route = NavScreen.Settings.route
        ) {
            AppNavDrawer(
                currentRoute = navHostController
            ) {
                SettingsScreen {
                    navHostController.navigateUp()
                }
            }
        }
        composable(
            route = NavScreen.Search.route
        ) {
            AppNavDrawer(
                currentRoute = navHostController
            ) {
                SearchScreen()
            }
        }
    }
}

@Composable
fun AppNavDrawer(
    currentRoute: NavHostController,
    content: @Composable () -> Unit,
) {
    val currentDestination = currentRoute.currentDestination?.route ?: ""

    val (home, search, settings) = remember { FocusRequester.createRefs() }
    NavigationDrawer(
        drawerContent = {
            val isSelected = it == DrawerValue.Closed
            Column(
                Modifier
                    .onFocusChanged {
                        if (it.isFocused) {
                            when (currentDestination) {
                                NavScreen.Home.route -> home
                                NavScreen.Search.route -> search
                                NavScreen.Settings.route -> settings
                                else -> home
                            }.requestFocus()
                        }
                    }
                    .focusable()) {
                NavigationDrawerItem(
                    modifier = Modifier.focusRequester(home),
                    selected = isSelected && currentDestination == NavScreen.Home.route,
                    onClick = {
                        currentRoute.navigate(NavScreen.Home.route)
                    },
                    leadingContent = {
                        Icon(Icons.Default.Home, contentDescription = "Home Icon")
                    },
                    content = {

                    })
                Spacer(Modifier.height(20.dp))
                NavigationDrawerItem(
                    modifier = Modifier.focusRequester(search),
                    selected = isSelected && currentDestination == NavScreen.Search.route,
                    onClick = {
                        currentRoute.navigate(NavScreen.Search.route)
                    },
                    leadingContent = {
                        Icon(Icons.Default.Search, contentDescription = "Search Icon")
                    },
                    content = {

                    })
                Spacer(Modifier.height(20.dp))
                NavigationDrawerItem(
                    modifier = Modifier.focusRequester(settings),
                    selected = isSelected && currentDestination == NavScreen.Settings.route,
                    onClick = {
                        currentRoute.navigate(NavScreen.Settings.route)
                    },
                    leadingContent = {
                        Icon(Icons.Default.Settings, contentDescription = "Settings Icon")
                    },
                    content = {

                    })
            }
        }, content = content
    )
}