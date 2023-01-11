package com.example.housify.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.HousifyHomeScreenContent
import com.example.housify.ui.HousifySearchScreen
import com.example.housify.ui.theme.HousifyAboutScreen

@Composable
fun HomeNavGraph(navController: NavHostController,screenNavController: NavController) {

    var isSearchPage by remember { mutableStateOf(false)}
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if (isSearchPage) {
                HousifySearchScreen { isSearchPage = false }

            } else {
                HousifyHomeScreenContent(onButtonClick = {screenNavController.navigate(MainScreens.Details.route) },
                onSearchClick = {isSearchPage = true})
            }
        }

        composable(route = BottomBarScreen.About.route) {
            HousifyAboutScreen()
        }
    }
}

