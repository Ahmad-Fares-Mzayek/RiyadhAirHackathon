package com.example.riyadhairhackathon.ui.components.map

import androidx.annotation.DrawableRes
import org.osmdroid.util.GeoPoint

data class Landmark(
    val name: String,
    val description: String,
    val latitude: Double,
    val longitude: Double,
    @DrawableRes val imageRes: Int
) {
    fun toGeoPoint(): GeoPoint = GeoPoint(latitude, longitude)
}
