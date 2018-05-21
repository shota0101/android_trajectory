package com.hayashi.android_trajectory.utility

import android.graphics.Color
import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.*
import com.hayashi.android_trajectory.extensions.toLatLng

object MyMap {
    val zoomLevel: Float = 16f
    val zoomLevelUp: Float = 4f // 日本列島が見える程度のズームレベル
    var map: GoogleMap? = null
    var curMarker: Marker? = null

    fun updateCurMarker(location: Location) {
        updateCurMarker(location.toLatLng())
    }

    fun updateCurMarker(latLng: LatLng) {
        if (curMarker == null) {
            map?.let {
                curMarker = it.addMarker(
                        MarkerOptions().position(latLng).title("現在地")
                )
                updateCamera(latLng)
            }
        } else {
            curMarker?.let {
                it.position = latLng
            }
        }
    }

    fun updateCamera(latLng: LatLng) {
        updateCamera(latLng, zoomLevel)
    }

    fun updateCamera(latLng: LatLng, zoomLevel: Float) {
        map?.let {
            it.moveCamera(CameraUpdateFactory.newLatLng(latLng))
            val cameraUpdate = CameraUpdateFactory.newLatLngZoom(
                    latLng,
                    zoomLevel)
            it.moveCamera(cameraUpdate)
        }
    }

    fun drawTrajectory(dots: ArrayList<LatLng>) {
        val polylineOptions = PolylineOptions()
        polylineOptions.addAll(dots)
        polylineOptions.color(Color.RED)
        this.map?.let {
            val polyline = it.addPolyline(polylineOptions)
            // 直前の色を変更
            polyline.setColor(Color.parseColor("#6495ed"))
            // 直前に丸みを付ける
            polyline.setStartCap(RoundCap())
            polyline.setEndCap(RoundCap())
        }
    }
}