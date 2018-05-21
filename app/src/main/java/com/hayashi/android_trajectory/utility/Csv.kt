package com.hayashi.android_trajectory.utility

import com.google.android.gms.maps.model.LatLng

class Csv(text: Text) {
    val text: Text = text

    fun readAsLatLng(): ArrayList<LatLng> {
        val fileContent: String = text.read()
        val lines: List<String> = fileContent.split("\n")
        val dots: ArrayList<LatLng> = ArrayList()
        for(line in lines) {
            val items: List<String> = line.split(",")
            if (items.size < 3)
                break
            dots.add(LatLng(
                    items[1].toDouble(),
                    items[2].toDouble()
            ))
        }
        return dots
    }
}