package com.hayashi.android_trajectory

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.hayashi.android_trajectory.utility.MyLog
import com.hayashi.android_trajectory.utility.MyMap
import com.hayashi.android_trajectory.utility.Text

class MapsActivity : FragmentActivity(), OnMapReadyCallback {

    private val l = MyLog("Loc")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
        start()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        MyMap.map = googleMap
        MyMap.updateCamera(
                LatLng(35.710063, 139.8107), // スカイツリーで初期化
                MyMap.zoomLevelUp
        )
    }

    private fun start() {
        l.d("MapsActivity")
        LocationListenerImpl.text = Text("location_log.csv", this)
        LocationStarter.start(this)
    }
}
