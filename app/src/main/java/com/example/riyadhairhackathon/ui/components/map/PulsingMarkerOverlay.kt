package com.example.riyadhairhackathon.ui.components.map

import android.animation.ValueAnimator
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.view.MotionEvent
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.Projection
import org.osmdroid.views.overlay.Overlay

class PulsingMarkerOverlay(
    private val location: GeoPoint,
    private val onClick: () -> Unit
) : Overlay() {

    private val innerPaint = Paint().apply {
        color = 0xFF6A397D.toInt() // Deep Purple
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private val outerPaint = Paint().apply {
        color = 0xFF6A397D.toInt()
        style = Paint.Style.FILL
        isAntiAlias = true
    }

    private var pulseScale = 1.0f
    private val animator = ValueAnimator.ofFloat(1.0f, 2.2f).apply {
        duration = 1500
        repeatCount = ValueAnimator.INFINITE
        repeatMode = ValueAnimator.RESTART
        addUpdateListener { animation ->
            pulseScale = animation.animatedValue as Float
            // Update alpha based on scale (fade out as it grows)
            val alpha = ((2.2f - pulseScale) / 1.2f * 100).toInt().coerceIn(0, 100)
            outerPaint.alpha = alpha
        }
    }

    init {
        animator.start()
    }

    override fun draw(canvas: Canvas?, mapView: MapView?, shadow: Boolean) {
        if (shadow || canvas == null || mapView == null) return

        val projection: Projection = mapView.projection
        val point = Point()
        projection.toPixels(location, point)

        val baseRadius = 20f // Base size of the marker

        // Draw pulsing outer circle
        canvas.drawCircle(
            point.x.toFloat(),
            point.y.toFloat(),
            baseRadius * pulseScale,
            outerPaint
        )

        // Draw solid inner circle
        canvas.drawCircle(
            point.x.toFloat(),
            point.y.toFloat(),
            baseRadius,
            innerPaint
        )
        
        // Request redraw for animation
        mapView.postInvalidate()
    }

    override fun onSingleTapConfirmed(e: MotionEvent?, mapView: MapView?): Boolean {
        if (e == null || mapView == null) return false

        val projection = mapView.projection
        val point = Point()
        projection.toPixels(location, point)

        val hitRadius = 50f // Hit area size
        val dx = e.x - point.x
        val dy = e.y - point.y
        val distance = Math.sqrt((dx * dx + dy * dy).toDouble())

        if (distance <= hitRadius) {
            onClick()
            return true
        }
        return false
    }
    
    fun stopAnimation() {
        animator.cancel()
    }
}
