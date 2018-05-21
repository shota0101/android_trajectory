package com.hayashi.android_trajectory

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import com.hayashi.android_trajectory.LocationStarter.locationProvider
import com.hayashi.android_trajectory.extensions.toCSV
import com.hayashi.android_trajectory.utility.MyLog
import com.hayashi.android_trajectory.utility.MyMap
import com.hayashi.android_trajectory.utility.Text
import java.util.*

object LocationListenerImpl : LocationListener {
    private val l = MyLog("LocationListenerImpl")
    var text: Text? = null

    // ロケーションプロバイダが利用可能になるとコールバックされるメソッド
    override fun onProviderEnabled(provider: String) {
        l.d("onProviderEnabled called : provider = $provider")
        locationProvider = provider
    }

    // 位置情報が通知されるたびにコールバックされるメソッド
    override fun onLocationChanged(location: Location) {
        l.d("onLocationChanged called : location = $location")
        text?.let {
            it.append("${Date()},${location.toCSV()}\n")
        }
        MyMap.updateCurMarker(location)
    }

    // ロケーションステータスが変わるとコールバックされるメソッド
    override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {
        l.d("onStatusChanged called : provider = $provider, status = $status, extras = $extras")
    }

    //ロケーションプロバイダが利用不可能になるとコールバックされるメソッド
    override fun onProviderDisabled(provider: String) {
        l.d("onProviderDisabled called : provider = $provider")
    }
}