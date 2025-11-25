package com.example.riyadhairhackathon.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.riyadhairhackathon.data.UserPreferences
import com.example.riyadhairhackathon.ui.components.PrimaryButton
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
import com.example.riyadhairhackathon.ui.theme.SuccessGreen
import kotlinx.coroutines.launch

@Composable
fun ChecklistScreen(userPreferences: UserPreferences) {
    val scope = rememberCoroutineScope()
    val checkedItems by userPreferences.checkedItems.collectAsState(initial = emptySet())

    val documents = listOf("Passport", "Boarding Pass", "Visa", "Travel Insurance")
    val essentials = listOf("Phone Charger", "Medications", "Wallet/Cards", "Keys")
    val allItems = documents + essentials
    
    val progress = if (allItems.isNotEmpty()) checkedItems.size.toFloat() / allItems.size else 0f

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))
    ) {
        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(DeepNavyPurple)
                .padding(24.dp)
        ) {
            Column {
                Text(
                    "Travel Essentials",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Color.White
                )
                Text(
                    "Don't leave anything behind",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.White.copy(alpha = 0.7f)
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            // Progress Bar Card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Progress", style = MaterialTheme.typography.titleSmall)
                        Text(
                            "${(progress * 100).toInt()}% Ready",
                            style = MaterialTheme.typography.titleSmall,
                            color = DeepNavyPurple
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    LinearProgressIndicator(
                        progress = { progress },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = SuccessGreen,
                        trackColor = Color.LightGray.copy(alpha = 0.3f)
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(24.dp))

            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item {
                    Text(
                        "Documents",
                        style = MaterialTheme.typography.titleMedium,
                        color = DeepNavyPurple,
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                    )
                }
                items(documents) { item ->
                    ChecklistItem(
                        label = item,
                        isChecked = checkedItems.contains(item),
                        onCheckedChange = { isChecked ->
                            scope.launch { userPreferences.toggleChecklistItem(item, isChecked) }
                        }
                    )
                }
                
                item {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        "Essentials",
                        style = MaterialTheme.typography.titleMedium,
                        color = DeepNavyPurple,
                        modifier = Modifier.padding(start = 4.dp, bottom = 4.dp)
                    )
                }
                items(essentials) { item ->
                    ChecklistItem(
                        label = item,
                        isChecked = checkedItems.contains(item),
                        onCheckedChange = { isChecked ->
                            scope.launch { userPreferences.toggleChecklistItem(item, isChecked) }
                        }
                    )
                }
            }
            
            PrimaryButton(
                text = "Reset Checklist",
                onClick = { scope.launch { userPreferences.resetChecklist() } },
                backgroundColor = Color.Gray.copy(alpha = 0.8f)
            )
        }
    }
}

@Composable
fun ChecklistItem(
    label: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp) // Flat look for list items
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isChecked,
                onCheckedChange = onCheckedChange,
                colors = CheckboxDefaults.colors(
                    checkedColor = SuccessGreen,
                    uncheckedColor = Color.Gray
                )
            )
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(start = 12.dp),
                color = if (isChecked) Color.Gray else Color.Black,
                textDecoration = if (isChecked) TextDecoration.LineThrough else null
            )
        }
    }
}
