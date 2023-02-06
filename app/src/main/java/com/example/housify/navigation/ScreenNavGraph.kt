package com.example.housify.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.HousifyDetailsScreen
import com.example.housify.ui.HousifyHome
import com.example.housify.ui.screens.HousifyViewModel

/**
 * Implements main screens(home,details) navigation graph.
 * Navigation between other screens(home,empty search and about) is handled in HousifyHome.
 */
@Composable
fun ScreenNavGraph(navController: NavHostController, viewModel: HousifyViewModel) {
    NavHost(navController = navController, startDestination = MainScreens.Home.route) {
        //Navigate to HomeScreen loader
        composable(route = MainScreens.Home.route) {
            HousifyHome(screenNavController = navController, viewModel = viewModel)
        }
        //Navigate to screen of each homes details
        composable(route = MainScreens.Details.route) {
            HousifyDetailsScreen(onBackClick = {
                navController.popBackStack()
                navController.navigate(MainScreens.Home.route)
            }, viewModel = viewModel)
        }
    }
}

