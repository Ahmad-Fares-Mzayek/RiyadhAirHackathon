package com.example.riyadhairhackathon.ui.screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.AirlineSeatReclineExtra
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.Wc
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riyadhairhackathon.R
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ARSeatFinderScreen(
    onNavigateBack: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "seat_pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 0.7f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Full-screen background
        Image(
            painter = painterResource(id = R.drawable.plane_interior),
            contentDescription = "Plane Interior",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp)
        )

        // 2. AR Overlays Canvas (Path & Highlight) - REMOVED
        // 3. UI Overlays

        // Header removed - handled by MainActivity

        // Seat Label (Above Highlight)
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(x = 60.dp, y = (-80).dp) // Position near seat highlight
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Icon(Icons.Default.CheckCircle, null, tint = Color(0xFF00E5FF), modifier = Modifier.size(24.dp))
                Text(
                    "Your Seat - 14C",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
        }

        // Emergency Exit Markers
        ARExitMarker(
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 25.dp, bottom = 120.dp),
            label = "EXIT"
        )
        
        // Bathroom Marker
        ARBathroomMarker(
            modifier = Modifier.align(Alignment.TopCenter).padding(top = 230.dp),
            label = "WC"
        )

        // Row Numbers (Floating in aisle) - REMOVED

        // Bottom Info Card
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(100.dp)
                .background(Color.Black.copy(alpha = 0.7f), RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Default.AirlineSeatReclineExtra, 
                    contentDescription = null, 
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        "Row 14, Seat C • Window Seat",
                        color = Color.White,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        "8 rows ahead",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
            }
        }
    }
}

@Composable
fun ARExitMarker(modifier: Modifier = Modifier, label: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(40.dp)
                .background(Color(0xFF4CAF50).copy(alpha = 0.4f), CircleShape)
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.ExitToApp, null, tint = Color.White)
        }
        Text(label, color = Color(0xFF4CAF50), fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ARBathroomMarker(modifier: Modifier = Modifier, label: String) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .background(Color(0xFF2196F3).copy(alpha = 0.4f), CircleShape)
                .border(2.dp, Color.White, CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Default.Wc, null, tint = Color.White)
        }
        Text(label, color = Color(0xFF2196F3), fontSize = 10.sp, fontWeight = FontWeight.Bold)
    }
}
