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
    object ARGate : Screen("ar_gate")
    object ARSeat : Screen("ar_seat")
    object Tour : Screen("tour")
}

@Composable
fun NavGraph(
    navController: NavHostController,
    userPreferences: UserPreferences,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
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
                onNavigateToGateAR = {
                    navController.navigate(Screen.ARGate.route)
                },
                onNavigateToSeatAR = {
                    navController.navigate(Screen.ARSeat.route)
                },
                onNavigateToChecklist = {
                    navController.navigate(Screen.Checklist.route)
                }
            )
        }

        composable(Screen.Checklist.route) {
            ChecklistScreen(userPreferences = userPreferences)
        }

        composable(Screen.ARGate.route) {
            ARGuideScreen(arMode = "GATE")
        }

        composable(Screen.ARSeat.route) {
            ARGuideScreen(arMode = "SEAT")
        }

        composable(Screen.Tour.route) {
            com.example.riyadhairhackathon.ui.screens.TourScreen()
        }
    }
}