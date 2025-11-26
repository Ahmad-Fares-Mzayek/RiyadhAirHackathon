package com.example.riyadhairhackathon.ui.components.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple

@Composable
fun BottomSheetContent(landmark: Landmark) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        // Placeholder Image
        Image(
            painter = painterResource(id = landmark.imageRes),
            contentDescription = landmark.name,
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.LightGray),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = landmark.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color = DeepNavyPurple
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Description
        Text(
            text = landmark.description,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Mock Distance
        Text(
            text = "~2.5 km away", // Mocked as requested
            style = MaterialTheme.typography.labelLarge,
            color = DeepNavyPurple,
            fontWeight = FontWeight.SemiBold
        )
        
        Spacer(modifier = Modifier.height(24.dp))
    }
}
