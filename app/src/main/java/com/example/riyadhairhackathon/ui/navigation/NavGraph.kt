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
    object Tour : Screen("tour")
    object Profile : Screen("profile")
    object Updates : Screen("updates")
    object Chat : Screen("chat")
    object ExploreCity : Screen("explore_city")
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
                onNavigateToChecklist = { navController.navigate(Screen.Checklist.route) },
                onNavigateToAR = { navController.navigate(Screen.ARGuide.route) },
                onNavigateToProfile = { navController.navigate(Screen.Profile.route) },
                onNavigateToUpdates = { navController.navigate(Screen.Updates.route) },
                onNavigateToChat = { navController.navigate(Screen.Chat.route) },
                onNavigateToExplore = { navController.navigate(Screen.ExploreCity.route) }
            )
        }
        composable(Screen.Checklist.route) {
            ChecklistScreen(
                userPreferences = userPreferences,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.ARGuide.route) {
            ARGuideScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Tour.route) {
            com.example.riyadhairhackathon.ui.screens.TourScreen()
        }
        composable(Screen.Profile.route) {
            com.example.riyadhairhackathon.ui.screens.ProfileScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Updates.route) {
            com.example.riyadhairhackathon.ui.screens.UpdatesScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.Chat.route) {
            com.example.riyadhairhackathon.ui.screens.ChatScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable(Screen.ExploreCity.route) {
            com.example.riyadhairhackathon.ui.screens.ExploreCityScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
