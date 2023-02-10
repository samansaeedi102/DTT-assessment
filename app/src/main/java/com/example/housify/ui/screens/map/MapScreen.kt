package com.example.housify.ui.screens.map

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import com.example.housify.R
import com.example.housify.data.network.Constant.GOOGLE_APP
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

/**
 * Shows location of each house on Google Maps
 */
@Composable
fun MapScreen(location: LatLng, onReady: (GoogleMap) -> Unit) {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    lifecycle.addObserver(RememberMapLifeCycle(map = mapView))
    val uri = stringResource(R.string.URI, "${location.latitude}", "${location.longitude}")
    AndroidView(factory = {
        mapView.apply {
            mapView.getMapAsync {
                val zoomLevel = 12f
                it.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
                it.addMarker(MarkerOptions().position(location))
                it.animateCamera(CameraUpdateFactory.newLatLngZoom(location, zoomLevel))
                it.uiSettings.isCompassEnabled = true
                it.uiSettings.isMapToolbarEnabled = false
                it.setOnMapClickListener {
                    val intent = Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(uri)
                    )
                    intent.setPackage(GOOGLE_APP)
                    startActivity(context, intent, null)
                }
            }
        }
    })
}

@Composable
fun RememberMapLifeCycle(map: MapView): LifecycleObserver {
    return remember {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> map.onCreate(Bundle())
                Lifecycle.Event.ON_START -> map.onStart()
                Lifecycle.Event.ON_RESUME -> map.onResume()
                Lifecycle.Event.ON_PAUSE -> map.onPause()
                Lifecycle.Event.ON_STOP -> map.onStop()
                Lifecycle.Event.ON_DESTROY -> map.onDestroy()
                Lifecycle.Event.ON_ANY -> throw IllegalStateException()
            }
        }
    }
}

