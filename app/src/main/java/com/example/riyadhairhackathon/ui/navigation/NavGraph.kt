package com.example.riyadhairhackathon.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.riyadhairhackathon.data.UserPreferences
import com.example.riyadhairhackathon.ui.screens.ARGuideScreen
import com.example.riyadhairhackathon.ui.screens.ChecklistScreen
import com.example.riyadhairhackathon.ui.screens.HomeScreen

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Home : Screen("home")
    object Checklist : Screen("checklist")
    object ARGuide : Screen("ar_guide")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    userPreferences: UserPreferences,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route, // Default, overridden in MainActivity if needed
        modifier = modifier
    ) {
        composable(Screen.Onboarding.route) {
            com.example.riyadhairhackathon.ui.screens.OnboardingScreen(
                userPreferences = userPreferences,
                onGetStarted = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        composable(Screen.Home.route) {
            HomeScreen(
                userPreferences = userPreferences,
                onNavigateToAR = { navController.navigate(Screen.ARGuide.route) },
                onNavigateToChecklist = { navController.navigate(Screen.Checklist.route) }
            )
        }
        composable(Screen.Checklist.route) {
            ChecklistScreen(userPreferences = userPreferences)
        }
        composable(Screen.ARGuide.route) {
            ARGuideScreen()
        }
    }
}
