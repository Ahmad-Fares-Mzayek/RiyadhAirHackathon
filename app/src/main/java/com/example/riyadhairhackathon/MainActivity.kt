package com.example.riyadhairhackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.riyadhairhackathon.data.UserPreferences
import com.example.riyadhairhackathon.ui.navigation.NavGraph
import com.example.riyadhairhackathon.ui.navigation.Screen
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.RiyadhAirHackathonTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val userPreferences = UserPreferences(this)

        setContent {
            RiyadhAirHackathonTheme {
                val isFirstLaunch by userPreferences.isFirstLaunch.collectAsState(initial = true)
                val navController = rememberNavController()

                LaunchedEffect(isFirstLaunch) {
                    if (isFirstLaunch) {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(0)
                        }
                    }
                }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val currentRoute = currentDestination?.route

                val showBottomBar = currentRoute != Screen.Onboarding.route
                // نخفي الـ TopBar في Onboarding و Tour (Tour عندها TopBar خاص فيها)
                val showTopBar =
                    currentRoute != Screen.Onboarding.route && currentRoute != Screen.Tour.route

                Scaffold(
                    topBar = {
                        if (showTopBar) {
                            val title = when (currentRoute) {
                                Screen.Home.route -> "PathFinder Air"
                                Screen.Checklist.route -> "Checklist"
                                Screen.ARGate.route,
                                Screen.ARSeat.route -> "AR Navigation"
                                else -> "PathFinder Air"
                            }

                            CenterAlignedTopAppBar(
                                title = { Text(title, color = Color.White) },
                                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                                    containerColor = DeepNavyPurple
                                )
                            )
                        }
                    },
                    bottomBar = {
                        if (showBottomBar) {
                            NavigationBar(
                                containerColor = Color.White
                            ) {
                                val items = listOf(
                                    Screen.Home to Icons.Default.Home,
                                    Screen.Tour to Icons.Default.LocationOn
                                )

                                items.forEach { (screen, icon) ->
                                    NavigationBarItem(
                                        icon = { androidx.compose.material3.Icon(icon, null) },
                                        label = {
                                            Text(
                                                when (screen) {
                                                    Screen.Home -> "Home"
                                                    Screen.Tour -> "Tour"
                                                    else -> screen.route
                                                }
                                            )
                                        },
                                        selected = currentDestination?.hierarchy?.any {
                                            it.route == screen.route
                                        } == true,
                                        onClick = {
                                            navController.navigate(screen.route) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        },
                                        colors = NavigationBarItemDefaults.colors(
                                            selectedIconColor = DeepNavyPurple,
                                            selectedTextColor = DeepNavyPurple,
                                            indicatorColor = DeepNavyPurple.copy(alpha = 0.2f)
                                        )
                                    )
                                }
                            }
                        }
                    }
                ) { innerPadding ->
                    NavGraph(
                        navController = navController,
                        userPreferences = userPreferences,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}