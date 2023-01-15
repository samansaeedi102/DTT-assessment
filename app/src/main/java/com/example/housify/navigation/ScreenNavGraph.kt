package com.example.housify.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.HousifyApp
import com.example.housify.ui.screens.SplashScreen
import com.example.housify.ui.HousifyDetailsScreen
import com.example.housify.ui.screens.HousifyViewModel

@Composable
fun ScreenNavGraph(navController: NavHostController) {
    val viewModel: HousifyViewModel = viewModel(factory = HousifyViewModel.Factory)
    NavHost(navController = navController, startDestination = MainScreens.Splash.route) {
        //Default navigation to splash screen
        composable(route = MainScreens.Splash.route) {
            SplashScreen(navController)
        }
        //Navigate to a HomeScreen loader
        composable(route = MainScreens.Home.route){
            HousifyApp(screenNavController = navController, viewModel = viewModel)
        }
        //Navigate to screen of each homes details
        //composable(route = MainScreens.Details.route){
          //  HousifyDetailsScreen(onBackClick = { navController.navigate(BottomBarScreen.Home.route) })
       // }
    }
}

