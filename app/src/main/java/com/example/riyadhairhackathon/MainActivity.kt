package com.example.riyadhairhackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
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
                
                // Determine start destination based on first launch
                LaunchedEffect(isFirstLaunch) {
                    if (isFirstLaunch) {
                        navController.navigate(Screen.Onboarding.route) {
                            popUpTo(0)
                        }
                    }
                }

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route
                val showBottomBar = currentRoute != Screen.Onboarding.route

                Scaffold(
                    topBar = {
                        if (showBottomBar) {
                            CenterAlignedTopAppBar(
                                title = { Text("PathFinder Air", color = Color.White) },
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
                                val currentDestination = navBackStackEntry?.destination
                                
                                val items = listOf(
                                    Screen.Home to Icons.Default.Home,
                                    Screen.Checklist to Icons.AutoMirrored.Filled.List,
                                    Screen.ARGuide to Icons.Default.LocationOn
                                )
                                
                                items.forEach { (screen, icon) ->
                                    NavigationBarItem(
                                        icon = { Icon(icon, contentDescription = null) },
                                        label = { Text(screen.route.replaceFirstChar { it.uppercase() }) },
                                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
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