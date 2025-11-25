package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.data.UserPreferences
import com.example.riyadhairhackathon.ui.components.ImageCard
import com.example.riyadhairhackathon.ui.components.InfoCard
import com.example.riyadhairhackathon.ui.components.PlaceholderImage
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.LightIndigo
import com.example.riyadhairhackathon.ui.theme.PrimaryPurple
import com.example.riyadhairhackathon.ui.theme.SuccessGreen
import com.example.riyadhairhackathon.ui.theme.WhiteBackground
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

@Composable
fun HomeScreen(
    userPreferences: UserPreferences,
    onNavigateToAR: () -> Unit,
    onNavigateToChecklist: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    // Mock Data
    val flightNumber = "RX 254"
    val route = "Riyadh (RUH) → Dubai (DXB)"
    val gate = "A23"
    val seat = "14C"
    
    // Set departure for today at 3:45 PM
    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 15)
        set(Calendar.MINUTE, 45)
        set(Calendar.SECOND, 0)
    }
    val departureTime = calendar.timeInMillis
    val boardingTime = departureTime - TimeUnit.MINUTES.toMillis(30) // 3:15 PM

    var timeRemaining by remember { mutableStateOf("Calculating...") }

    LaunchedEffect(Unit) {
        while(true) {
            val now = System.currentTimeMillis()
            val diff = boardingTime - now
            
            if (diff > 0) {
                val hours = TimeUnit.MILLISECONDS.toHours(diff)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60
                timeRemaining = "Board in ${hours}h ${minutes}m"
            } else {
                timeRemaining = "Boarding Now"
            }
            delay(10000) // Update every 10 seconds
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF5F5F5)) // Light gray background for the whole screen
    ) {
        // Top Section with Gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(DeepNavyPurple, LightIndigo)
                    )
                )
                .padding(24.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Good Afternoon, Traveler",
                            style = MaterialTheme.typography.titleMedium,
                            color = Color.White.copy(alpha = 0.8f)
                        )
                        Text(
                            text = SimpleDateFormat("EEE, MMM d", Locale.getDefault()).format(Date()),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                    }
                    // User Avatar
                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(id = com.example.riyadhairhackathon.R.drawable.user_avatar),
                        contentDescription = "User",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                }
                
                Spacer(modifier = Modifier.height(32.dp))
                
                // Flight Info Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = WhiteBackground),
                    elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = flightNumber,
                                style = MaterialTheme.typography.titleLarge,
                                color = DeepNavyPurple,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                            )
                            Surface(
                                color = SuccessGreen.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(8.dp)
                            ) {
                                Text(
                                    text = "On Time",
                                    color = SuccessGreen,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            androidx.compose.foundation.Image(
                                painter = androidx.compose.ui.res.painterResource(id = com.example.riyadhairhackathon.R.drawable.ic_plane),
                                contentDescription = "Plane",
                                modifier = Modifier.size(24.dp),
                                contentScale = androidx.compose.ui.layout.ContentScale.Fit
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = route,
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.DarkGray
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            FlightDetailItem(label = "Gate", value = gate)
                            FlightDetailItem(label = "Seat", value = seat)
                            FlightDetailItem(label = "Boarding", value = "3:15 PM")
                        }
                        
                        Spacer(modifier = Modifier.height(24.dp))
                        
                        // Countdown Timer
                        Surface(
                            color = DeepNavyPurple,
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = timeRemaining,
                                color = Color.White,
                                style = MaterialTheme.typography.titleMedium,
                                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                                modifier = Modifier.padding(vertical = 12.dp)
                            )
                        }
                    }
                }
            }
        }

        // Quick Actions Grid
        Column(modifier = Modifier.padding(24.dp)) {
            Text(
                text = "Quick Actions",
                style = MaterialTheme.typography.titleLarge,
                color = DeepNavyPurple,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ImageCard(
                        title = "Checklist",
                        subtitle = "Travel Essentials",
                        icon = Icons.AutoMirrored.Filled.List,
                        imageRes = com.example.riyadhairhackathon.R.drawable.bg_checklist,
                        onClick = onNavigateToChecklist
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    ImageCard(
                        title = "AR Guide",
                        subtitle = "Find Gate $gate",
                        icon = Icons.Default.LocationOn,
                        imageRes = com.example.riyadhairhackathon.R.drawable.bg_airport_guide,
                        onClick = onNavigateToAR
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Box(modifier = Modifier.weight(1f)) {
                    ImageCard(
                        title = "Seat Finder",
                        subtitle = "Locate $seat",
                        icon = Icons.Default.LocationOn,
                        imageRes = com.example.riyadhairhackathon.R.drawable.bg_seat_finder,
                        onClick = onNavigateToAR // Goes to same AR screen, logic handles mode
                    )
                }
                Box(modifier = Modifier.weight(1f)) {
                    ImageCard(
                        title = "Updates",
                        subtitle = "Flight Status",
                        icon = Icons.Default.Notifications,
                        imageRes = com.example.riyadhairhackathon.R.drawable.bg_flight_updates,
                        onClick = { /* TODO: Navigate to updates */ }
                    )
                }
            }
        }
        
        // Image Replacement Guidance
        /*
         * IMAGE REPLACEMENT GUIDANCE
         * 
         * 1. Riyadh Air Logo:
         *    - Location: ui/screens/OnboardingScreen.kt
         *    - Recommended Size: 200x200dp
         *    - Source: Official Brand Assets
         * 
         * 2. User Avatar:
         *    - Location: ui/screens/HomeScreen.kt
         *    - Recommended Size: 100x100dp
         *    - Source: User Profile or Placeholder
         * 
         * 3. Card Backgrounds (Checklist, AR Guide, Seat Finder, Updates):
         *    - Location: ui/screens/HomeScreen.kt
         *    - Recommended Size: 400x400dp (Square/Landscape)
         *    - Source: Unsplash (Airport, Travel, Plane themes)
         * 
         * HOW TO REPLACE:
         * - Add images to res/drawable
         * - Replace PlaceholderImage composables with Image(painter = painterResource(id = R.drawable.your_image), ...)
         * - For ImageCard, pass the painter as a parameter and use it in the Box background.
         */
    }
}

@Composable
fun FlightDetailItem(label: String, value: String) {
    Column {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = Color.Gray
        )
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = DeepNavyPurple,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
        )
    }
}
