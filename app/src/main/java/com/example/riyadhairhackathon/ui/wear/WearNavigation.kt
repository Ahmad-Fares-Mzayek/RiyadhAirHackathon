package com.example.riyadhairhackathon.ui.wear

import androidx.compose.runtime.Composable
import androidx.wear.compose.navigation.SwipeDismissableNavHost
import androidx.wear.compose.navigation.composable
import androidx.wear.compose.navigation.rememberSwipeDismissableNavController

@Composable
fun WearOSApp() {
    val navController = rememberSwipeDismissableNavController()

    SwipeDismissableNavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable("home") {
            WearHomeScreen(
                onNavigateToChecklist = { navController.navigate("checklist") },
                onNavigateToDestination = { navController.navigate("destination") }
            )
        }
        composable("checklist") {
            WearChecklistScreen()
        }
        composable("destination") {
            WearDestinationScreen()
        }
    }
}
