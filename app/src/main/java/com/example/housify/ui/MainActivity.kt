package com.example.housify.ui

import android.Manifest
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.housify.R
import com.example.housify.navigation.ScreenNavGraph
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.theme.HousifyTheme
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val LOCATION_PERMISSION_CODE = 1
    private val viewModel by viewModels<HousifyViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value
            }
        }
        setContent {
            HousifyTheme {
                val viewModel = hiltViewModel<HousifyViewModel>()
                ScreenNavGraph(navController = rememberNavController(), viewModel)
            }
        }
    }

    /**
     * When app starts, loads all houses and if app loses and receives focus again, regenerate the houses.
     */
    override fun onResume() {
        super.onResume()
        getLocationPermission()
        getDeviceLocation()
        viewModel.deleteCurrentHousesOnResume()
        viewModel.getHouses()
    }

    /**
     * When app stops,deletes all houses so that after gaining focus, onResume, houses get generated again.
     * This was implemented to be able to reload houses with DISTANCE property after granting permission.
     * Permission request takes application to onPause status. So this approach helped to overcome the issue
     * of having houses with distance 0.
     */
    override fun onPause() {
        super.onPause()
        viewModel.deleteCurrentHousesOnPause()
    }


    private fun getLocationPermission() {
        if (ContextCompat.checkSelfPermission(
                this.applicationContext,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            viewModel.grantLocationPermission(true)
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            AlertDialog.Builder(this)
                .setTitle(getString(R.string.permission_needed))
                .setMessage(getString(R.string.must_grant_permission))
                .setPositiveButton("ok", DialogInterface.OnClickListener(
                    fun(dialog: DialogInterface, which) {
                        ActivityCompat.requestPermissions(
                            this,
                            arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                            LOCATION_PERMISSION_CODE
                        )
                    }
                ))
                .setNegativeButton("cancel", DialogInterface.OnClickListener(
                    fun(dialog: DialogInterface, which) {
                        dialog.dismiss()
                    }
                ))
                .create().show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_CODE
            )
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                viewModel.grantLocationPermission(true)
            } else {
                viewModel.grantLocationPermission(false)
            }
        }
    }

    private fun getDeviceLocation() {
        val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        try {
            if (viewModel.locationPermissionGranted.value == true) {
                val locationResult = fusedLocationProviderClient.lastLocation

                locationResult.addOnCompleteListener { task ->
                    val result = locationResult.result
                    viewModel.currentUserLatLng(
                        LatLng(result.latitude, result.longitude)
                    )
                }

            }

        } catch (e: SecurityException) {
            Toast.makeText(this, getString(R.string.location_unavailable), Toast.LENGTH_SHORT).show()
        }
    }
}
