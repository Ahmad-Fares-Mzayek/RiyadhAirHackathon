package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.SuccessGreen
import com.example.riyadhairhackathon.ui.theme.WhiteBackground

data class FlightUpdate(
    val title: String,
    val message: String,
    val timestamp: String,
    val type: UpdateType
)

enum class UpdateType {
    INFO, WARNING, SUCCESS
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdatesScreen(
    onNavigateBack: () -> Unit
) {
    val updates = listOf(
        FlightUpdate(
            "Baggage Information",
            "Your checked baggage will arrive at Carousel 7",
            "30 minutes ago",
            UpdateType.INFO
        ),
        FlightUpdate(
            "Boarding Started",
            "Boarding has begun for flight RX 254. Please proceed to gate A27.",
            "1 hour ago",
            UpdateType.SUCCESS
        ),
        FlightUpdate(
            "Flight Delay",
            "Your flight RX 254 is delayed by 30 minutes. New departure: 4:15 PM",
            "2 hours ago",
            UpdateType.WARNING
        ),
        FlightUpdate(
            "Gate Change",
            "Gate changed from A23 to A27. Please proceed to new gate.",
            "3 hours ago",
            UpdateType.WARNING
        ),
        FlightUpdate(
            "Weather Advisory",
            "Clear weather expected at destination. On-time arrival likely.",
            "4 hours ago",
            UpdateType.INFO
        ),
        FlightUpdate(
            "Terminal Update",
            "Check-in moved to Terminal 3, Counter 15-20",
            "5 hours ago",
            UpdateType.INFO
        ),
        FlightUpdate(
            "Lounge Access",
            "You have access to Al Fursan Lounge. Valid until 3:00 PM",
            "6 hours ago",
            UpdateType.SUCCESS
        )
    )

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Flight Updates", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DeepNavyPurple
                )
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(WhiteBackground)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(updates) { update ->
                UpdateCard(update)
            }
        }
    }
}

@Composable
fun UpdateCard(update: FlightUpdate) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {
            // Icon
            val (icon, color) = when (update.type) {
                UpdateType.INFO -> Icons.Default.Info to Color.Blue
                UpdateType.WARNING -> Icons.Default.Warning to Color(0xFFFFA000) // Amber
                UpdateType.SUCCESS -> Icons.Default.Notifications to SuccessGreen
            }
            
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = update.title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = DeepNavyPurple
                    )
                    Text(
                        text = update.timestamp,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                
                Spacer(modifier = Modifier.height(4.dp))
                
                Text(
                    text = update.message,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.DarkGray
                )
            }
        }
    }
}
