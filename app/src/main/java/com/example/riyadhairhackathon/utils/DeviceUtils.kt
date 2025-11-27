package com.example.riyadhairhackathon.utils

import android.content.Context
import android.content.pm.PackageManager

fun isWearOS(context: Context): Boolean {
    return context.packageManager.hasSystemFeature(PackageManager.FEATURE_WATCH)
}
