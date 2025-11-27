package com.example.riyadhairhackathon.ui.screens

import android.Manifest
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.ui.components.PrimaryButton
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.LightIndigo
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults

@OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
@Composable
fun ARGuideScreen(
    onNavigateBack: () -> Unit
) {
    val cameraPermissionState = rememberPermissionState(Manifest.permission.CAMERA)
    var isArActive by remember { mutableStateOf(false) }
    var arMode by remember { mutableStateOf("") } // "GATE" or "SEAT"
    
    // Mock Data - No user input
    val targetGate = "A23"
    val targetSeat = "14C"

    if (isArActive) {
        // Show AR View
        if (arMode == "GATE") {
            ARAirportGuideScreen(
                onNavigateBack = { isArActive = false }
            )
        } else {
            ARSeatFinderScreen(
                onNavigateBack = { isArActive = false }
            )
        }
    } else {
        // Selection Screen
        Scaffold(
            // Top bar handled by MainActivity
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Text(
                    "Use AR to navigate the airport seamlessly. Your flight details are already loaded.",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Gray
                )
                
                // Gate Finder Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text("Navigate to Gate", style = MaterialTheme.typography.titleMedium)
                        Text(targetGate, style = MaterialTheme.typography.displaySmall, color = DeepNavyPurple)
                        Spacer(modifier = Modifier.height(16.dp))
                        PrimaryButton(
                            text = "Start AR Navigation",
                            onClick = {
                                arMode = "GATE"
                                if (cameraPermissionState.status.isGranted) {
                                    isArActive = true
                                } else {
                                    cameraPermissionState.launchPermissionRequest()
                                }
                            }
                        )
                    }
                }

                // Seat Finder Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(24.dp)) {
                        Text("Find My Seat", style = MaterialTheme.typography.titleMedium)
                        Text(targetSeat, style = MaterialTheme.typography.displaySmall, color = LightIndigo)
                        Spacer(modifier = Modifier.height(16.dp))
                        PrimaryButton(
                            text = "Find Seat",
                            onClick = {
                                arMode = "SEAT"
                                if (cameraPermissionState.status.isGranted) {
                                    isArActive = true
                                } else {
                                    cameraPermissionState.launchPermissionRequest()
                                }
                            },
                            backgroundColor = LightIndigo
                        )
                    }
                }
                
                Spacer(modifier = Modifier.weight(1f))
                
                Text(
                    "In production: Data pulled from user's booking via API",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
