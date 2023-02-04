package com.example.housify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.HousifyHome
import com.example.housify.ui.HousifyDetailsScreen
import com.example.housify.ui.screens.HousifyViewModel

@Composable
fun ScreenNavGraph(navController: NavHostController, viewModel: HousifyViewModel) {
    NavHost(navController = navController, startDestination = MainScreens.Home.route) {
        //Navigate to a HomeScreen loader
        composable(route = MainScreens.Home.route){
            HousifyHome(screenNavController = navController, viewModel = viewModel, housifyUiState = viewModel.housifyUiState)
        }
        //Navigate to screen of each homes details
        composable(route = MainScreens.Details.route){
           HousifyDetailsScreen(onBackClick = {
               navController.popBackStack()
               navController.navigate(MainScreens.Home.route)}, viewModel = viewModel)
       }
    }
}

