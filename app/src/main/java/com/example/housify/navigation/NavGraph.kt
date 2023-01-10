package com.example.housify.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.SplashScreen
import com.example.housify.ui.HousifyDetailsScreen

import com.example.housify.ui.HousifyHomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.Splash.route) {
        //Default navigation to splash screen
        composable(route = Screen.Splash.route) {
            SplashScreen(navController)
        }
        //Navigate to a Homescreen loader
        composable(route = Screen.Home.route){
            HousifyHomeScreen(navRootController = navController)
        }
        //Navigate to screen of each homes details
        composable(route = Screen.Details.route){
            HousifyDetailsScreen()
        }
    }
}

