package com.example.housify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.screens.SplashScreen
import com.example.housify.ui.HousifyDetailsScreen
import com.example.housify.ui.HousifyRootScreen

@Composable
fun ScreenNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = MainScreens.Splash.route) {
        //Default navigation to splash screen
        composable(route = MainScreens.Splash.route) {
            SplashScreen(navController)
        }
        //Navigate to a HomeScreen loader
        composable(route = MainScreens.Home.route){
            HousifyRootScreen(screenNavController = navController)
        }
        //Navigate to screen of each homes details
        composable(route = MainScreens.Details.route){
            HousifyDetailsScreen()
        }
    }
}

