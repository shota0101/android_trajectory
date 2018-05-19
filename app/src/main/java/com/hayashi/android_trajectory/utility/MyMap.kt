package com.hayashi.android_trajectory.utility

import android.location.Location
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
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
}