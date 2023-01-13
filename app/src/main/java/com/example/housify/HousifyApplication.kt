package com.example.housify

import android.app.Application
import com.example.housify.data.AppContainer
import com.example.housify.data.DefaultAppContainer

class HousifyApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}