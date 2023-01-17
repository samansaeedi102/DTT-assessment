package com.example.housify.map

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationRequest
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

import java.util.*

@Composable
fun MapScreen(location: LatLng, onReady: (GoogleMap) -> Unit) {
    lateinit var locationRequest: LocationRequest
    val context = LocalContext.current
    val mapView = remember {MapView(context)}
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    var userLocation: Location


    lifecycle.addObserver(RememberMapLifeCycle(map = mapView))

    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync {
                val zoomLevel = 12f
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
                it.addMarker(MarkerOptions().position(location))
                it.animateCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
                it.uiSettings.isZoomControlsEnabled = true
                it.uiSettings.isCompassEnabled = true
            }
        }
    })
}


@Composable
fun RememberMapLifeCycle(map: MapView) : LifecycleObserver {
    return remember {
        LifecycleEventObserver { source, event ->
            when(event) {
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START ->map.onStart()
                Lifecycle.Event.ON_RESUME ->map.onResume()
                Lifecycle.Event.ON_PAUSE ->map.onPause()
                Lifecycle.Event.ON_STOP ->map.onStop()
                Lifecycle.Event.ON_DESTROY ->map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}




var currentLocation = Location("Place")
@Composable
fun showDistance(houseLocation: Location, context: Context): String {
    var fusedLocationClient: FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)
    var result: Float?
    var resultFormat: String? = null
    checkPermission()
    fusedLocationClient.lastLocation
        .addOnSuccessListener {
            if(it != null) {
                currentLocation.latitude = it.latitude
                currentLocation.longitude = it.longitude
            }
        }
    result = currentLocation.distanceTo(houseLocation)
    resultFormat = "%.0f".format(result / 1000)

    return resultFormat.toString()
}
@Composable
fun checkPermission() {
    if (ActivityCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            LocalContext.current,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            LocalContext.current as Activity,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100)
        return
    }
}


