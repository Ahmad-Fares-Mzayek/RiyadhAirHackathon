package com.example.riyadhairhackathon.ui.wear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.Chip
import androidx.wear.compose.material.ChipDefaults
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.rememberScalingLazyListState
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.PrimaryPurple

@Composable
fun WearHomeScreen(
    onNavigateToChecklist: () -> Unit,
    onNavigateToDestination: () -> Unit
) {
    val listState = rememberScalingLazyListState()

    Scaffold(
        timeText = { TimeText() },
        positionIndicator = { PositionIndicator(scalingLazyListState = listState) }
    ) {
        ScalingLazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            state = listState,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                // Logo Placeholder
                androidx.compose.foundation.Image(
                    painter = androidx.compose.ui.res.painterResource(id = com.example.riyadhairhackathon.R.drawable.user_avatar),
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(32.dp)
                        .clip(CircleShape),
                    contentScale = androidx.compose.ui.layout.ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Text(
                    text = "RX 254",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }

            item {
                Text(
                    text = "Riyadh → Dubai",
                    fontSize = 16.sp,
                    color = PrimaryPurple // Lavender
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "5h 38m",
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "until departure",
                        fontSize = 12.sp,
                        color = PrimaryPurple
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    InfoCard(
                        label = "Gate A23",
                        modifier = Modifier.weight(1f)
                    )
                    InfoCard(
                        label = "Seat 14C",
                        modifier = Modifier.weight(1f)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Text(
                    text = "Boards at 3:15 PM",
                    fontSize = 14.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth(0.9f)
                ) {
                    CompactChip(
                        label = "Checklist",
                        onClick = onNavigateToChecklist,
                        modifier = Modifier.weight(1f)
                    )
                    CompactChip(
                        label = "Destination",
                        onClick = onNavigateToDestination,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        }
    }
}

@Composable
fun InfoCard(label: String, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .background(DeepNavyPurple, RoundedCornerShape(12.dp))
            .padding(vertical = 12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            fontSize = 16.sp, // Slightly smaller to fit side-by-side
            fontWeight = FontWeight.Bold,
            color = Color.White
        )
    }
}

@Composable
fun CompactChip(label: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Chip(
        onClick = onClick,
        label = {
            Text(
                text = label,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                fontSize = 10.sp
            )
        },
        colors = ChipDefaults.chipColors(backgroundColor = DeepNavyPurple),
        modifier = modifier.height(40.dp)
    )
}
