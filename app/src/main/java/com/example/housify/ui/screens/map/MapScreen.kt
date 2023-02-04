package com.example.housify.ui.screens.map

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

@Composable
fun MapScreen(location: LatLng, onReady: (GoogleMap) -> Unit) {
    val context = LocalContext.current
    val mapView = remember {MapView(context)}
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(rememberMapLifeCycle(map = mapView))
    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync {
                val zoomLevel = 12f
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
                it.addMarker(MarkerOptions().position(location))
                it.animateCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
                it.uiSettings.isCompassEnabled = true
                it.uiSettings.isMapToolbarEnabled = false
                it.setOnMapClickListener  {
                    val intent = Intent(Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q=${location.latitude},${location.longitude}"))
                    intent.setPackage("com.google.android.apps.maps")
                    startActivity(context, intent, null)
                }
            }
        }
    })
}

@Composable
fun rememberMapLifeCycle(map: MapView) : LifecycleObserver {
    return remember {
        LifecycleEventObserver { _, event ->
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



//A variable to save the location needed for function below
var currentLocation = Location("Place")

//Calculate the distance of current user to each house
@Composable
fun showDistance(houseLocation: Location, context: Context): Int {
    val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)
    checkPermission(context)
    fusedLocationClient.lastLocation
        .addOnSuccessListener {
            if (it != null) {
                currentLocation = it
            }
        }
    return (currentLocation.distanceTo(houseLocation) / 1000).toInt()
}
//Check location permission needed for calculating the distance
fun checkPermission(context: Context) {
    if (ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED
    ) {
        ActivityCompat.requestPermissions(
            context as Activity,
            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
        return
    }
}


