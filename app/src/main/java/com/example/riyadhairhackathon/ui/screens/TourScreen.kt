package com.example.riyadhairhackathon.ui.screens

import android.Manifest
import android.preference.PreferenceManager
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.riyadhairhackathon.R
import com.example.riyadhairhackathon.ui.components.PrimaryButton
import com.example.riyadhairhackathon.ui.components.map.BottomSheetContent
import com.example.riyadhairhackathon.ui.components.map.Landmark
import com.example.riyadhairhackathon.ui.components.map.PulsingMarkerOverlay
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TourScreen() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    
    // Initialize OSMDroid configuration
    LaunchedEffect(Unit) {
        Configuration.getInstance().load(context, PreferenceManager.getDefaultSharedPreferences(context))
    }

    val locationPermissionsState = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect(Unit) {
        if (!locationPermissionsState.allPermissionsGranted) {
            locationPermissionsState.launchMultiplePermissionRequest()
        }
    }

    // State for selected landmark
    var selectedLandmark by remember { mutableStateOf<Landmark?>(null) }
    val sheetState = rememberModalBottomSheetState()

    // Mock Landmarks
    val landmarks = remember {
        listOf(
            Landmark(
                name = "Kingdom Tower",
                description = "One of the most iconic skyscrapers in Saudi Arabia, offering breathtaking views of Riyadh.",
                latitude = 24.7116,
                longitude = 46.6744,
                imageRes = R.drawable.pic1
            ),
            Landmark(
                name = "Boulevard City",
                description = "A premier entertainment zone featuring global restaurants, cafes, and live performances.",
                latitude = 24.7695,
                longitude = 46.6318,
                imageRes = R.drawable.pic2
            ),
            Landmark(
                name = "Diriyah",
                description = "The historic birthplace of the first Saudi state, featuring traditional mud-brick architecture.",
                latitude = 24.7333,
                longitude = 46.5750,
                imageRes = R.drawable.pic3
            ),
            Landmark(
                name = "Riyadh Zoo",
                description = "A popular family destination housing diverse animal species and lush green spaces.",
                latitude = 24.6767,
                longitude = 46.7417,
                imageRes = R.drawable.pic4
            ),
            Landmark(
                name = "King Abdulaziz Historical Center",
                description = "A cultural landmark showcasing the history and heritage of Saudi Arabia.",
                latitude = 24.6475,
                longitude = 46.7106,
                imageRes = R.drawable.pic5
            )
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Tour", color = Color.White) },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = DeepNavyPurple
                )
            )
        }
    ) { innerPadding ->
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            if (locationPermissionsState.allPermissionsGranted || locationPermissionsState.permissions.any { it.status.isGranted }) {
                
                val mapView = remember {
                    MapView(context).apply {
                        setTileSource(TileSourceFactory.MAPNIK)
                        setMultiTouchControls(true)
                        controller.setZoom(12.0) // Zoom out slightly to see markers
                        // Default to Riyadh
                        controller.setCenter(GeoPoint(24.7136, 46.6753))
                    }
                }

                // Manage MapView lifecycle
                DisposableEffect(lifecycleOwner) {
                    val observer = LifecycleEventObserver { _, event ->
                        when (event) {
                            Lifecycle.Event.ON_RESUME -> mapView.onResume()
                            Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                            else -> {}
                        }
                    }
                    lifecycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifecycleOwner.lifecycle.removeObserver(observer)
                    }
                }

                AndroidView(
                    factory = { 
                        mapView.apply {
                            // Add Location Overlay
                            val locationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), this)
                            locationOverlay.enableMyLocation()
                            locationOverlay.enableFollowLocation()
                            overlays.add(locationOverlay)

                            // Add Pulsing Markers
                            landmarks.forEach { landmark ->
                                val marker = PulsingMarkerOverlay(GeoPoint(landmark.latitude, landmark.longitude)) {
                                    selectedLandmark = landmark
                                }
                                overlays.add(marker)
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
                
            } else {
                Column(
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(innerPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Location permission is required to show your tour.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 16.dp)
                    )
                    PrimaryButton(
                        text = "Grant Permission",
                        onClick = { locationPermissionsState.launchMultiplePermissionRequest() }
                    )
                }
            }

            // Bottom Sheet
            if (selectedLandmark != null) {
                ModalBottomSheet(
                    onDismissRequest = { selectedLandmark = null },
                    sheetState = sheetState,
                    containerColor = Color.White
                ) {
                    selectedLandmark?.let { landmark ->
                        BottomSheetContent(landmark = landmark)
                    }
                }
            }
        }
    }
}
