package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.R
import com.example.riyadhairhackathon.ui.components.ImageCard
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.LightIndigo
import com.example.riyadhairhackathon.ui.theme.SuccessGreen
import com.example.riyadhairhackathon.ui.theme.WhiteBackground
import kotlinx.coroutines.delay
import java.time.format.DateTimeFormatter

@Composable
fun HomeScreen(
    onNavigateToChecklist: () -> Unit,
    onNavigateToAR: () -> Unit,
    onNavigateToProfile: () -> Unit,
    onNavigateToUpdates: () -> Unit,
    onNavigateToChat: () -> Unit,
    onNavigateToExplore: () -> Unit
) {
    val scrollState = rememberScrollState()
    
    // Mock Data
    val flightNumber = "RX 254"
    val route = "Riyadh (RUH) → Dubai (DXB)"
    val gate = "A23"
    val seat = "14C"
    
    // Smart Timer Logic
    var timeRemaining by remember { mutableStateOf("Loading...") }
    
    LaunchedEffect(Unit) {
        while (true) {
            val now = java.time.LocalDateTime.now()
            val departure = java.time.LocalDateTime.now().withHour(16).withMinute(15).withSecond(0)
            
            if (now.isAfter(departure)) {
                timeRemaining = "Departed"
            } else {
                val duration = java.time.Duration.between(now, departure)
                val hours = duration.toHours()
                val minutes = duration.toMinutes() % 60
                
                timeRemaining = if (hours > 0) {
                    val hText = if (hours == 1L) "1 hour" else "$hours hours"
                    val mText = if (minutes == 1L) "1 minute" else "$minutes minutes"
                    "$hText and $mText until departure"
                } else {
                    if (minutes > 0) {
                        val mText = if (minutes == 1L) "1 minute" else "$minutes minutes"
                        "$mText until departure"
                    } else {
                        "Less than 1 minute until departure"
                    }
                }
            }
            delay(60000) // Update every minute
        }
    }

    // Scaffold removed - handled by MainActivity
    
    // Floating Action Button for Chat
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(DeepNavyPurple) // Fix: Root background is now Purple to blend with header
                .verticalScroll(scrollState)
        ) {
            // Header Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                // Background Gradient
                Box(
                    modifier = Modifier
                        .matchParentSize()
                        .background(
                            androidx.compose.ui.graphics.Brush.verticalGradient(
                                colors = listOf(DeepNavyPurple, LightIndigo)
                            )
                        )
                )
                
                Column(modifier = Modifier.padding(24.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = getGreeting(name = "Mohammed"),
                                style = MaterialTheme.typography.headlineMedium,
                                color = Color.White,
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = java.time.LocalDate.now().format(DateTimeFormatter.ofPattern("EEEE, MMM d")),
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.White.copy(alpha = 0.8f)
                            )
                        }
                        
                        Spacer(modifier = Modifier.width(16.dp))
                        
                        // User Avatar
                        androidx.compose.foundation.Image(
                            painter = painterResource(id = R.drawable.user_avatar),
                            contentDescription = "User",
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .clickable(onClick = onNavigateToProfile),
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
                                    fontWeight = FontWeight.Bold
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
                                    painter = painterResource(id = R.drawable.ic_plane),
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
            
            // Quick Actions Section
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = WhiteBackground,
                shape = RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)
            ) {
                Column(modifier = Modifier.padding(24.dp)) {
                    Text(
                        text = "Quick Actions",
                        style = MaterialTheme.typography.titleLarge,
                        color = DeepNavyPurple,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    
                    // Row 1: AR Guide (Full Width)
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ImageCard(
                            title = "AR Guide",
                            subtitle = "Find Gate $gate",
                            icon = Icons.Default.LocationOn,
                            imageRes = R.drawable.bg_airport_guide,
                            onClick = onNavigateToAR
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))

                    // Row 2: AI Travel Suggestions (Full Width)
                    Box(modifier = Modifier.fillMaxWidth()) {
                        ImageCard(
                            title = "AI Travel Suggestions",
                            subtitle = "Explore Dubai",
                            icon = Icons.Default.Star,
                            imageRes = R.drawable.travel_suggestions, // Using seat finder bg as requested
                            onClick = onNavigateToExplore
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(16.dp))
                    
                    // Row 3: Checklist & Updates
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Box(modifier = Modifier.weight(1f)) {
                            ImageCard(
                                title = "Checklist",
                                subtitle = "Essentials",
                                icon = Icons.AutoMirrored.Filled.List,
                                imageRes = R.drawable.bg_checklist,
                                onClick = onNavigateToChecklist
                            )
                        }
                        Box(modifier = Modifier.weight(1f)) {
                            ImageCard(
                                title = "Updates",
                                subtitle = "Flight Status",
                                icon = Icons.Default.Notifications,
                                imageRes = R.drawable.bg_flight_updates,
                                onClick = onNavigateToUpdates
                            )
                        }
                    }
                    
                    Spacer(modifier = Modifier.height(32.dp)) // Bottom padding
                }
            }
        }
        
        FloatingActionButton(
            onClick = onNavigateToChat,
            containerColor = DeepNavyPurple,
            contentColor = Color.White,
            shape = CircleShape,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "AI Assistant"
            )
        }
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
            fontWeight = FontWeight.Bold
        )
    }
}

fun getGreeting(name: String = "Traveler"): String {
    val hour = java.time.LocalTime.now().hour
    return when (hour) {
        in 5..11 -> "Good Morning, $name"
        in 12..17 -> "Good Afternoon, $name"
        else -> "Good Evening, $name"
    }
}
