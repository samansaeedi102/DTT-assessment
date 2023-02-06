package com.example.housify.ui

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.BottomBarScreen
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.theme.HousifyAboutScreen
import com.example.housify.utils.BottomBar

/**
 * Creates navigation graph for navigating between options of BottomNavigationBar.
 */
@Composable
fun HousifyHome(
    viewModel: HousifyViewModel = hiltViewModel(),
    bottomBarNavController: NavHostController = rememberNavController(),
    screenNavController: NavController,
) {
    Scaffold(
        bottomBar = { BottomBar(navController = bottomBarNavController) }
    ) {
        NavHost(
            navController = bottomBarNavController,
            startDestination = BottomBarScreen.Home.route
        ) {
            composable(route = BottomBarScreen.Home.route) {
                HousifyHomeScreenContent(viewModel, screenNavController = screenNavController)
            }
            composable(route = BottomBarScreen.About.route) {
                HousifyAboutScreen()
            }
        }
    }
}



