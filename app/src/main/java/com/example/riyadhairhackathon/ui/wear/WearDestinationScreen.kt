package com.example.riyadhairhackathon.ui.wear

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material.PositionIndicator
import androidx.wear.compose.material.Scaffold
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import androidx.wear.compose.material.TimeText
import androidx.wear.compose.material.rememberScalingLazyListState

import androidx.compose.foundation.Image
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.riyadhairhackathon.R
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple

@Composable
fun WearDestinationScreen() {
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
                Text(
                    text = "Dubai",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                // Hero Image Placeholder
                Image(
                    painter = painterResource(id = R.drawable.p0),
                    contentDescription = "Dubai Skyline",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                DestinationInfoCard(
                    iconPlaceholder = {
                        Image(
                            painter = painterResource(id = R.drawable.weather),
                            contentDescription = "Weather",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    title = "28°C",
                    subtitle = "Sunny"
                )
            }

            item {
                DestinationInfoCard(
                    iconPlaceholder = {
                        Image(
                            painter = painterResource(id = R.drawable.time),
                            contentDescription = "Time",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    title = "2:45 PM",
                    subtitle = "GMT+4"
                )
            }

            item {
                DestinationInfoCard(
                    iconPlaceholder = {
                        Image(
                            painter = painterResource(id = R.drawable.currency),
                            contentDescription = "Currency",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    title = "AED",
                    subtitle = "1 SAR = 0.98 AED"
                )
            }

            item {
                DestinationInfoCard(
                    iconPlaceholder = {
                        Image(
                            painter = painterResource(id = R.drawable.lang),
                            contentDescription = "Language",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    title = "Arabic & English",
                    subtitle = "Widely spoken"
                )
            }

            item {
                DestinationInfoCard(
                    iconPlaceholder = {
                        Image(
                            painter = painterResource(id = R.drawable.tip),
                            contentDescription = "Tip",
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    },
                    title = "Quick Tip",
                    subtitle = "Modest dress in public"
                )
            }
        }
    }
}

@Composable
fun DestinationInfoCard(
    iconPlaceholder: @Composable () -> Unit,
    title: String,
    subtitle: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(DeepNavyPurple, RoundedCornerShape(12.dp))
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(32.dp)
                .background(Color.White.copy(alpha = 0.2f), RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            iconPlaceholder()
        }
        
        Spacer(modifier = Modifier.width(12.dp))
        
        Column {
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = subtitle,
                fontSize = 12.sp,
                color = Color.White.copy(alpha = 0.8f)
            )
        }
    }
}
