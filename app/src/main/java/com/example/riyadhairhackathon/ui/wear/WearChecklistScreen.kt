package com.example.riyadhairhackathon.ui.wear

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import androidx.wear.compose.material.ToggleChip
import androidx.wear.compose.material.ToggleChipDefaults
import androidx.wear.compose.material.rememberScalingLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import com.example.riyadhairhackathon.ui.theme.DeepNavyPurple
@Composable
fun WearChecklistScreen() {
    val listState = rememberScalingLazyListState()
    
    // Simple state for checklist items
    var passportChecked by remember { mutableStateOf(true) }
    var boardingPassChecked by remember { mutableStateOf(true) }
    var phoneChecked by remember { mutableStateOf(true) }
    var luggageChecked by remember { mutableStateOf(true) }

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
                    text = "Travel Checklist",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }

            item {
                ChecklistItem(
                    label = "Passport",
                    checked = passportChecked,
                    onCheckedChange = { passportChecked = it }
                )
            }

            item {
                ChecklistItem(
                    label = "Boarding Pass",
                    checked = boardingPassChecked,
                    onCheckedChange = { boardingPassChecked = it }
                )
            }

            item {
                ChecklistItem(
                    label = "Phone Charged",
                    checked = phoneChecked,
                    onCheckedChange = { phoneChecked = it }
                )
            }

            item {
                ChecklistItem(
                    label = "Luggage Tagged",
                    checked = luggageChecked,
                    onCheckedChange = { luggageChecked = it }
                )
            }
        }
    }
}

@Composable
fun ChecklistItem(
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    ToggleChip(
        checked = checked,
        onCheckedChange = onCheckedChange,
        label = {
            Text(
                text = label,
                fontSize = 16.sp,
                color = Color.White
            )
        },
        toggleControl = {
            if (checked) {
                androidx.wear.compose.material.Icon(
                    imageVector = androidx.compose.material.icons.Icons.Default.Check,
                    contentDescription = "Checked",
                    tint = Color.White
                )
            }
        },
        colors = ToggleChipDefaults.toggleChipColors(
            checkedStartBackgroundColor = DeepNavyPurple,
            checkedEndBackgroundColor = DeepNavyPurple,
            uncheckedStartBackgroundColor = DeepNavyPurple.copy(alpha = 0.5f),
            uncheckedEndBackgroundColor = DeepNavyPurple.copy(alpha = 0.5f),
            checkedContentColor = Color.White,
            uncheckedContentColor = Color.White
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    )
}
