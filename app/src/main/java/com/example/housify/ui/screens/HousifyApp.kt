package com.example.housify.ui


import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.HomeNavGraph
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.utils.BottomBar

//Implementing two navControllers for Screens and BottomNavigation
@Composable
fun HousifyApp(
    viewModel: HousifyViewModel,
    bottomBarNavController: NavHostController = rememberNavController(),
    screenNavController: NavController
) {
    Scaffold(
        bottomBar = {  BottomBar(navController = bottomBarNavController) }
    ) {
        HomeNavGraph(navController = bottomBarNavController,
            screenNavController = screenNavController,
            housifyUiState = viewModel.housifyUiState, viewModel= viewModel)
    }

}

