package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.R
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.LightIndigo
import com.example.riyadhairhackathon.ui.theme.SuccessGreen

data class TravelActivity(
    val title: String,
    val description: String,
    val imageRes: Int,
    val duration: String,
    val bestTime: String,
    val price: String,
    val rating: Double,
    val tags: List<String>
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreCityScreen(
    onNavigateBack: () -> Unit
) {
    val activities = remember {
        listOf(
            TravelActivity(
                "Burj Khalifa",
                "Soar to the top of the world's tallest building for breathtaking panoramic views of the city skyline and desert beyond.",
                R.drawable.p1,
                "2-3 hours",
                "Sunset",
                "$$$",
                4.9,
                listOf("Iconic", "Views", "Luxury")
            ),
            TravelActivity(
                "Desert Safari Adventure",
                "Experience the thrill of dune bashing, camel riding, and a traditional BBQ dinner under the stars in the Arabian desert.",
                R.drawable.p2,
                "6 hours",
                "Afternoon",
                "$$",
                4.8,
                listOf("Adventure", "Nature", "Culture")
            ),
            TravelActivity(
                "The Dubai Mall",
                "Shop 'til you drop at the world's largest destination for shopping, entertainment, and leisure, featuring the Dubai Aquarium.",
                R.drawable.p3,
                "4+ hours",
                "Anytime",
                "Free Entry",
                4.7,
                listOf("Shopping", "Indoor", "Family")
            ),
            TravelActivity(
                "Gold Souk",
                "Wander through the glittering lanes of the traditional gold market and haggle for spices and textiles in the nearby souks.",
                R.drawable.p4,
                "1-2 hours",
                "Evening",
                "Free Entry",
                4.5,
                listOf("Culture", "Market", "History")
            ),
            TravelActivity(
                "Jumeirah Beach",
                "Relax on the pristine white sands with views of the Burj Al Arab, or enjoy water sports in the turquoise waters.",
                R.drawable.p5,
                "3 hours",
                "Morning",
                "Free",
                4.6,
                listOf("Beach", "Relax", "Outdoors")
            ),
            TravelActivity(
                "Dubai Fountain Show",
                "Witness the world's largest choreographed fountain system set to music and lights at the foot of the Burj Khalifa.",
                R.drawable.p6,
                "30 mins",
                "Night",
                "Free",
                4.9,
                listOf("Show", "Nightlife", "Romantic")
            ),
            TravelActivity(
                "Al Fahidi Historical District",
                "Step back in time and explore the traditional wind-tower architecture and winding alleyways of old Dubai.",
                R.drawable.p7,
                "2 hours",
                "Morning",
                "Free",
                4.4,
                listOf("History", "Architecture", "Walking")
            ),
            TravelActivity(
                "Museum of the Future",
                "Explore the future of science, technology, and innovation in one of the most beautiful buildings in the world.",
                R.drawable.p8,
                "2-3 hours",
                "Morning",
                "$$$",
                4.8,
                listOf("Science", "Future", "Museum")
            )
        )
    }

    Scaffold(
        // Top bar handled by MainActivity
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.White),
            contentPadding = PaddingValues(bottom = 32.dp)
        ) {


            // Hero Section
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.p0), // Using pic1 as Dubai hero
                        contentDescription = "Dubai",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                    
                    // Gradient Overlay
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Transparent,
                                        Color.Black.copy(alpha = 0.7f)
                                    ),
                                    startY = 100f
                                )
                            )
                    )

                    // City Info
                    Column(
                        modifier = Modifier
                            .align(Alignment.BottomStart)
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Discover Dubai",
                            style = MaterialTheme.typography.displayMedium,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "A city of superlatives, where traditional Arabian culture meets futuristic innovation.",
                            style = MaterialTheme.typography.bodyLarge,
                            color = Color.White.copy(alpha = 0.9f),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Stats Row
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CityStat("28°C", "Temp")
                            CityStat("AED", "Currency")
                            CityStat("GMT+4", "Time")
                        }
                    }
                }
            }
            // Top Action Button
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Button(
                        onClick = { /* TODO: Personalized flow */ },
                        colors = ButtonDefaults.buttonColors(containerColor = DeepNavyPurple),
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            "Create itinerary based on AI Recommendations",
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }

            // Section Title
            item {
                Text(
                    text = "Top Experiences",
                    style = MaterialTheme.typography.headlineSmall,
                    color = DeepNavyPurple,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(24.dp)
                )
            }

            // Activities List
            items(activities) { activity ->
                ActivityCard(activity)
            }
            
            // Footer
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
                    Spacer(modifier = Modifier.height(24.dp))
                    Text(
                        text = "Powered by AI Recommendations",
                        style = MaterialTheme.typography.labelMedium,
                        color = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun CityStat(value: String, label: String) {
    Column {
        Text(
            text = value,
            style = MaterialTheme.typography.titleMedium,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = Color.White.copy(alpha = 0.7f)
        )
    }
}

@Composable
fun ActivityCard(activity: TravelActivity) {
    var isBookmarked by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 8.dp)
            .clickable { /* TODO: Open details */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column {
            // Image Section
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                Image(
                    painter = painterResource(id = activity.imageRes),
                    contentDescription = activity.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
                
                // Bookmark Button
                IconButton(
                    onClick = { isBookmarked = !isBookmarked },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .background(Color.White.copy(alpha = 0.8f), CircleShape)
                        .size(36.dp)
                ) {
                    Icon(
                        imageVector = if (isBookmarked) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = "Bookmark",
                        tint = if (isBookmarked) Color.Red else Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
                
                // Rating Badge
                Surface(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(12.dp),
                    color = Color.White,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = "Rating",
                            tint = Color(0xFFFFD700), // Gold
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = activity.rating.toString(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            // Content Section
            Column(modifier = Modifier.padding(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = activity.title,
                            style = MaterialTheme.typography.titleLarge,
                            color = DeepNavyPurple,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = activity.description,
                            style = MaterialTheme.typography.bodyMedium,
                            color = Color.Gray,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Tags Row
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(activity.tags) { tag ->
                        Surface(
                            color = LightIndigo.copy(alpha = 0.1f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = tag,
                                style = MaterialTheme.typography.labelSmall,
                                color = DeepNavyPurple,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.3f))
                
                Spacer(modifier = Modifier.height(12.dp))

                // Details Footer
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = activity.duration,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                        Text(
                            text = " • ",
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                        Text(
                            text = activity.bestTime,
                            style = MaterialTheme.typography.labelMedium,
                            color = Color.Gray
                        )
                    }
                    
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = activity.price,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold,
                            color = SuccessGreen
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Surface(
                            shape = CircleShape,
                            color = DeepNavyPurple.copy(alpha = 0.1f),
                            modifier = Modifier.clickable { /* TODO: Map */ }
                        ) {
                            Icon(
                                imageVector = Icons.Default.LocationOn,
                                contentDescription = "Map",
                                tint = DeepNavyPurple,
                                modifier = Modifier.padding(8.dp).size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}
