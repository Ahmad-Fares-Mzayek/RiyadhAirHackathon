package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.data.UserPreferences
import com.example.riyadhairhackathon.ui.components.ImageCard
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.LightIndigo
import com.example.riyadhairhackathon.ui.theme.SuccessGreen
import com.example.riyadhairhackathon.ui.theme.WhiteBackground
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit

@Composable
fun HomeScreen(
    userPreferences: UserPreferences,
    onNavigateToGateAR: () -> Unit,
    onNavigateToSeatAR: () -> Unit,
    onNavigateToChecklist: () -> Unit
) {
    val scrollState = rememberScrollState()

    val flightNumber = "RX 254"
    val route = "Riyadh (RUH) → Dubai (DXB)"
    val gate = "A23"
    val seat = "14C"

    val calendar = Calendar.getInstance().apply {
        set(Calendar.HOUR_OF_DAY, 15)
        set(Calendar.MINUTE, 45)
        set(Calendar.SECOND, 0)
    }
    val departureTime = calendar.timeInMillis
    val boardingTime = departureTime - TimeUnit.MINUTES.toMillis(30)

    var timeRemaining by remember { mutableStateOf("Calculating...") }

    LaunchedEffect(Unit) {
        while (true) {
            val now = System.currentTimeMillis()
            val diff = boardingTime - now

            timeRemaining = if (diff > 0) {
                val hours = TimeUnit.MILLISECONDS.toHours(diff)
                val minutes = TimeUnit.MILLISECONDS.toMinutes(diff) % 60
                "Board in ${hours}h ${minutes}m"
            } else {
                "Boarding Now"
            }
            delay(10000)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .background(Color(0xFFF5F5F5))
    ) {
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
                            text = SimpleDateFormat(
                                "EEE, MMM d",
                                Locale.getDefault()
                            ).format(Date()),
                            style = MaterialTheme.typography.headlineSmall,
                            color = Color.White
                        )
                    }

                    androidx.compose.foundation.Image(
                        painter = androidx.compose.ui.res.painterResource(
                            id = com.example.riyadhairhackathon.R.drawable.user_avatar
                        ),
                        contentDescription = "User",
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape),
                        contentScale = androidx.compose.ui.layout.ContentScale.Crop
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

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
                                    modifier = Modifier.padding(
                                        horizontal = 8.dp,
                                        vertical = 4.dp
                                    )
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            androidx.compose.foundation.Image(
                                painter = androidx.compose.ui.res.painterResource(
                                    id = com.example.riyadhairhackathon.R.drawable.ic_plane
                                ),
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
                        onClick = onNavigateToGateAR
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
                        onClick = onNavigateToSeatAR
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
    }
}

@Composable
fun FlightDetailItem(label: String, value: String) {
    androidx.compose.foundation.layout.Column {
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
