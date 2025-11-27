package com.example.riyadhairhackathon

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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
                TirhalApp(userPreferences)
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TirhalApp(userPreferences: UserPreferences) {
    val context = androidx.compose.ui.platform.LocalContext.current
    
    if (com.example.riyadhairhackathon.utils.isWearOS(context)) {
        com.example.riyadhairhackathon.ui.wear.WearOSApp()
    } else {
        // Phone UI
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
        val showBottomBar = currentRoute in listOf(
            Screen.Home.route,
            Screen.Tour.route
        )

        Scaffold(
            topBar = {
                if (currentRoute != Screen.Onboarding.route) {
                    val title = when (currentRoute) {
                        Screen.Home.route -> "Tirhal"
                        Screen.Tour.route -> "Tirhal Tour"
                        Screen.Checklist.route -> "Tirhal Checklist"
                        Screen.ARGuide.route -> "Tirhal AR Guide"
                        Screen.Profile.route -> "Profile"
                        Screen.Updates.route -> "Flight Updates"
                        Screen.Chat.route -> "Tirhal Assistant"
                        Screen.ExploreCity.route -> "Tirhal Explore"
                        else -> "Tirhal"
                    }
                    
                    val canNavigateBack = currentRoute !in listOf(Screen.Home.route, Screen.Tour.route)
                    
                    CenterAlignedTopAppBar(
                        title = { Text(title, color = Color.White) },
                        navigationIcon = {
                            if (canNavigateBack) {
                                IconButton(onClick = { navController.popBackStack() }) {
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                        contentDescription = "Back",
                                        tint = Color.White
                                    )
                                }
                            }
                        },
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
                            Screen.Tour to Icons.Default.LocationOn // Using LocationOn as a map placeholder
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