package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.BitmapDescriptorFactory

data class AirportLocation(
    val position: LatLng,
    val title: String,
    val snippet: String,
    val type: LocationType
)

enum class LocationType {
    TERMINAL, GATE, LOUNGE, RESTAURANT, RESTROOM, SHOPPING, PARKING, INFO
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TourScreen() {

    val airportLocations = remember {
        listOf(
            AirportLocation(
                LatLng(24.9578, 46.6988),
                "Main Terminal",
                "Terminal 1 - International Flights",
                LocationType.TERMINAL
            ),
            AirportLocation(
                LatLng(24.9568, 46.6978),
                "Gate 15",
                "International Departures",
                LocationType.GATE
            ),
            AirportLocation(
                LatLng(24.9588, 46.6998),
                "Al Fursan Lounge",
                "Premium Lounge - Terminal 1",
                LocationType.LOUNGE
            ),
            AirportLocation(
                LatLng(24.9570, 46.6990),
                "Starbucks",
                "Coffee & Snacks",
                LocationType.RESTAURANT
            ),
            AirportLocation(
                LatLng(24.9575, 46.6985),
                "Duty Free",
                "Tax-free Shopping",
                LocationType.SHOPPING
            ),
            AirportLocation(
                LatLng(24.9580, 46.6995),
                "Parking Area",
                "Short & Long Term Parking",
                LocationType.PARKING
            )
        )
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(LatLng(24.9578, 46.6988), 15f)
    }

    var selectedLocation by remember { mutableStateOf<AirportLocation?>(null) }
    var showLocationsList by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Airport Tour", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DeepNavyPurple
                ),
                actions = {
                    TextButton(onClick = { showLocationsList = !showLocationsList }) {
                        Text("List", color = Color.White)
                    }
                }
            )
        },
        floatingActionButton = {
            Column(
                horizontalAlignment = Alignment.End,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                FloatingActionButton(
                    onClick = { /* My Location */ },
                    containerColor = DeepNavyPurple
                ) {
                    Text("📍", style = MaterialTheme.typography.titleLarge)
                }

                FloatingActionButton(
                    onClick = { /* AR View */ },
                    containerColor = MaterialTheme.colorScheme.secondary
                ) {
                    Text("📷", style = MaterialTheme.typography.titleLarge)
                }
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                properties = MapProperties(
                    isMyLocationEnabled = false
                ),
                uiSettings = MapUiSettings(
                    zoomControlsEnabled = true,
                    compassEnabled = true
                )
            ) {
                airportLocations.forEach { location ->
                    Marker(
                        state = MarkerState(position = location.position),
                        title = location.title,
                        snippet = location.snippet,
                        icon = BitmapDescriptorFactory.defaultMarker(
                            when (location.type) {
                                LocationType.TERMINAL -> BitmapDescriptorFactory.HUE_BLUE
                                LocationType.GATE -> BitmapDescriptorFactory.HUE_GREEN
                                LocationType.LOUNGE -> BitmapDescriptorFactory.HUE_VIOLET
                                LocationType.RESTAURANT -> BitmapDescriptorFactory.HUE_ORANGE
                                LocationType.SHOPPING -> BitmapDescriptorFactory.HUE_ROSE
                                LocationType.PARKING -> BitmapDescriptorFactory.HUE_CYAN
                                else -> BitmapDescriptorFactory.HUE_RED
                            }
                        ),
                        onClick = {
                            selectedLocation = location
                            true
                        }
                    )
                }
            }

            if (showLocationsList) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.White
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Key Locations",
                                style = MaterialTheme.typography.titleLarge,
                                color = DeepNavyPurple
                            )
                            TextButton(onClick = { showLocationsList = false }) {
                                Text("✕", style = MaterialTheme.typography.titleLarge)
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        airportLocations.forEach { location ->
                            LocationItem(
                                location = location,
                                onClick = {
                                    selectedLocation = location
                                    showLocationsList = false
                                }
                            )
                        }
                    }
                }
            }

            selectedLocation?.let { location ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.BottomCenter),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = DeepNavyPurple
                    ),
                    elevation = CardDefaults.cardElevation(8.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(modifier = Modifier.weight(1f)) {
                                Text(
                                    text = location.title,
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.White
                                )
                                Text(
                                    text = location.snippet,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.White.copy(alpha = 0.8f)
                                )
                            }
                            TextButton(onClick = { selectedLocation = null }) {
                                Text("✕", color = Color.White, style = MaterialTheme.typography.titleLarge)
                            }
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        Row(
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = { /* Navigate */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = Color.White
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("🧭 Navigate", color = DeepNavyPurple)
                            }

                            Button(
                                onClick = { /* AR View */ },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.secondary
                                ),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text("📷 AR View")
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun LocationItem(
    location: AirportLocation,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = DeepNavyPurple.copy(alpha = 0.1f)
        )
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = when (location.type) {
                    LocationType.TERMINAL -> "✈"
                    LocationType.GATE -> "🚪"
                    LocationType.LOUNGE -> "🛋"
                    LocationType.RESTAURANT -> "🍽"
                    LocationType.SHOPPING -> "🛍"
                    LocationType.PARKING -> "🅿"
                    else -> "ℹ"
                },
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = location.title,
                    style = MaterialTheme.typography.bodyLarge,
                    color = DeepNavyPurple
                )
                Text(
                    text = location.snippet,
                    style = MaterialTheme.typography.bodySmall,
                    color = DeepNavyPurple.copy(alpha = 0.7f)
                )
            }
        }
    }
}