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
import androidx.compose.material.icons.filled.LocalCafe
import androidx.compose.material.icons.filled.ShoppingBag
import androidx.compose.material.icons.filled.Weekend
import androidx.compose.material.icons.filled.Wc
import androidx.compose.material.icons.filled.Explore
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
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riyadhairhackathon.R
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ARAirportGuideScreen(
    onNavigateBack: () -> Unit
) {
    val infiniteTransition = rememberInfiniteTransition(label = "ar_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue = 1.0f,
        targetValue = 1.1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse"
    )

    Box(modifier = Modifier.fillMaxSize()) {
        // 1. Full-screen background
        Image(
            painter = painterResource(id = R.drawable.airport_interior),
            contentDescription = "Airport Interior",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .blur(2.dp)
        )

        // 2. AR Overlays Canvas
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height

            // Main Directional Arrow (Cyan)
            val arrowPath = Path().apply {
                moveTo(width / 2, height * 0.2f)
                lineTo(width / 2 - 40.dp.toPx(), height * 0.2f + 60.dp.toPx())
                lineTo(width / 2 - 15.dp.toPx(), height * 0.2f + 60.dp.toPx())
                lineTo(width / 2 - 15.dp.toPx(), height * 0.2f + 120.dp.toPx())
                lineTo(width / 2 + 15.dp.toPx(), height * 0.2f + 120.dp.toPx())
                lineTo(width / 2 + 15.dp.toPx(), height * 0.2f + 60.dp.toPx())
                lineTo(width / 2 + 40.dp.toPx(), height * 0.2f + 60.dp.toPx())
                close()
            }
            
            // Shadow
            drawPath(
                path = arrowPath,
                color = Color.Black.copy(alpha = 0.4f),
                style = androidx.compose.ui.graphics.drawscope.Fill
            )
            
            // Arrow with Pulse
            scale(scale = pulseScale, pivot = Offset(width / 2, height * 0.25f)) {
                drawPath(
                    path = arrowPath,
                    color = Color(0xFF82FF1F), // Bright Green
                )
                // Glow effect (simulated with stroke)
                drawPath(
                    path = arrowPath,
                    color = Color.White.copy(alpha = 0.3f),
                    style = Stroke(width = 4.dp.toPx())
                )
            }
        }

        // Particle Effect Overlay
        Canvas(modifier = Modifier.fillMaxSize()) {
            val width = size.width
            val height = size.height
            repeat(50) {
                val x = (Math.random() * width).toFloat()
                val y = (Math.random() * height).toFloat()
                val radius = (Math.random() * 3 + 1).toFloat()
                val alpha = (Math.random() * 0.5 + 0.1).toFloat()
                drawCircle(
                    color = Color.White.copy(alpha = alpha),
                    radius = radius,
                    center = Offset(x, y)
                )
            }
        }

        // 3. UI Overlays
        
        // Header removed - handled by MainActivity

        // Distance Label (Below Arrow)
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 195.dp) // Below arrow
                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(16.dp))
                .border(2.dp, Color.White.copy(alpha = 0.5f), RoundedCornerShape(16.dp))
                .padding(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Text(
                "Gate A23 → 150m ahead",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium.copy(
                    shadow = Shadow(color = Color.Black, blurRadius = 4f)
                )
            )
        }

        // POI Markers (Scatter these)
        // Coffee Shop (Left, Mid)
        ARPointOfInterest(
            modifier = Modifier.align(Alignment.CenterStart).padding(start = 20.dp, bottom = 50.dp),
            icon = Icons.Default.LocalCafe,
            label = "Café",
            color = Color(0xFF240454)
        )

        // Restrooms (Right, Mid)
        ARPointOfInterest(
            modifier = Modifier.align(Alignment.CenterEnd).padding(end = 40.dp, bottom = 40.dp),
            icon = Icons.Default.Wc,
            label = "Restrooms",
            color = Color(0xFF4A90E2)
        )

        // Duty Free (Left, Lower)
        ARPointOfInterest(
            modifier = Modifier.align(Alignment.BottomStart).padding(start = 10.dp, bottom = 200.dp),
            icon = Icons.Default.ShoppingBag,
            label = "Duty Free",
            color = Color(0xFFFFD700)
        )
        
        // Lounge (Right, Upper)
        ARPointOfInterest(
            modifier = Modifier.align(Alignment.TopEnd).padding(end = 5.dp, top = 400.dp),
            icon = Icons.Default.Weekend,
            label = "Lounge",
            color = Color(0xFF8B7BA8)
        )

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
                    Icons.Default.Explore, 
                    contentDescription = null, 
                    tint = Color(0xFF82FF1F),
                    modifier = Modifier.size(32.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    "Follow the cyan arrow to reach Gate A23",
                    color = Color.White,
                    fontSize = 14.sp
                )
            }
        }
    }
}

@Composable
fun ARPointOfInterest(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    label: String,
    color: Color
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .background(color.copy(alpha = 0.6f), RoundedCornerShape(50))
                .border(1.dp, Color.White, RoundedCornerShape(50))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(label, color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Medium)
            }
        }
        // Dotted line to ground (simulated)
        Canvas(modifier = Modifier.height(40.dp).width(2.dp)) {
            drawLine(
                color = Color.White.copy(alpha = 0.3f),
                start = Offset(size.width/2, 0f),
                end = Offset(size.width/2, size.height),
                pathEffect = PathEffect.dashPathEffect(floatArrayOf(10f, 10f), 0f)
            )
        }
    }
}
