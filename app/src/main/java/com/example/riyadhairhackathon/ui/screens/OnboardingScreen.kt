package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.data.UserPreferences
import com.example.riyadhairhackathon.ui.components.PrimaryButton
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import kotlinx.coroutines.launch

@Composable
fun OnboardingScreen(
    userPreferences: UserPreferences,
    onGetStarted: () -> Unit
) {
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))

        Image(
            painter = androidx.compose.ui.res.painterResource(
                id = com.example.riyadhairhackathon.R.drawable.riyadh_air_logo
            ),
            contentDescription = "Riyadh Air Logo",
            modifier = Modifier.size(120.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(32.dp))

        Text(
            text = "Tirhal",
            style = MaterialTheme.typography.displayMedium,
            color = DeepNavyPurple,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Your AI-Powered Travel Companion",
            style = MaterialTheme.typography.titleMedium,
            color = DeepNavyPurple.copy(alpha = 0.7f),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Experience a seamless journey with real-time flight updates, AR navigation, and smart checklists tailored for your trip.",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Gray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 16.dp)
        )

        Spacer(modifier = Modifier.weight(1f))

        PrimaryButton(
            text = "Get Started",
            onClick = {
                scope.launch {
                    userPreferences.completeOnboarding()
                    onGetStarted()
                }
            }
        )

        Spacer(modifier = Modifier.height(32.dp))
    }
}
