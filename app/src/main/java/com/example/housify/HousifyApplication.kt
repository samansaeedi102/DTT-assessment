package com.example.housify

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Makes the container in order to implement Dagger-Hilt
 */
@HiltAndroidApp
class HousifyApplication : Application()