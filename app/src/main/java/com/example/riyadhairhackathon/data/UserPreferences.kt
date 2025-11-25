package com.example.riyadhairhackathon.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_prefs")

class UserPreferences(private val context: Context) {

    companion object {
        val FLIGHT_TIME_KEY = longPreferencesKey("flight_time")
        val FLIGHT_TYPE_KEY = stringPreferencesKey("flight_type")
        val CHECKED_ITEMS_KEY = stringSetPreferencesKey("checked_items")
        val IS_FIRST_LAUNCH_KEY = booleanPreferencesKey("is_first_launch")
    }

    val flightTime: Flow<Long?> = context.dataStore.data.map { prefs ->
        prefs[FLIGHT_TIME_KEY]
    }

    val flightType: Flow<String?> = context.dataStore.data.map { prefs ->
        prefs[FLIGHT_TYPE_KEY]
    }

    val checkedItems: Flow<Set<String>> = context.dataStore.data.map { prefs ->
        prefs[CHECKED_ITEMS_KEY] ?: emptySet()
    }

    val isFirstLaunch: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[IS_FIRST_LAUNCH_KEY] ?: true
    }

    suspend fun saveFlightInfo(time: Long, type: String) {
        context.dataStore.edit { prefs ->
            prefs[FLIGHT_TIME_KEY] = time
            prefs[FLIGHT_TYPE_KEY] = type
        }
    }

    suspend fun toggleChecklistItem(item: String, isChecked: Boolean) {
        context.dataStore.edit { prefs ->
            val current = prefs[CHECKED_ITEMS_KEY] ?: emptySet()
            if (isChecked) {
                prefs[CHECKED_ITEMS_KEY] = current + item
            } else {
                prefs[CHECKED_ITEMS_KEY] = current - item
            }
        }
    }

    suspend fun resetChecklist() {
        context.dataStore.edit { prefs ->
            prefs.remove(CHECKED_ITEMS_KEY)
        }
    }

    suspend fun completeOnboarding() {
        context.dataStore.edit { prefs ->
            prefs[IS_FIRST_LAUNCH_KEY] = false
        }
    }
}
