package com.hayashi.android_trajectory.extensions

import android.location.Location
import com.google.android.gms.maps.model.LatLng

fun Location.toLatLng(): LatLng {
    return LatLng(this.latitude, this.longitude)
}

fun Location.toCSV(): String {
    return "${this.latitude},${this.longitude}"
}