package com.example.housify.navigation

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.ErrorScreen
import com.example.housify.ui.HousifyHomeScreenContent
import com.example.housify.ui.HousifySearchScreen
import com.example.housify.ui.screens.HousifyUiState
import com.example.housify.ui.theme.HousifyAboutScreen

@Composable
fun HomeNavGraph(navController: NavHostController,screenNavController: NavController,housifyUiState: HousifyUiState) {
    var isSearchPage by remember { mutableStateOf(false)}
    when(housifyUiState) {
        is HousifyUiState.Success -> HousifyHomeScreenContent(housifyUiState = housifyUiState.houses,
            onButtonClick = {screenNavController.navigate(MainScreens.Details.route) },
            onSearchClick = {isSearchPage = true})
        is HousifyUiState.Error -> ErrorScreen()
        is HousifyUiState.Loading -> ErrorScreen()

    }
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if (isSearchPage) {
                HousifySearchScreen { isSearchPage = false }
            } else{
                Button(onClick = { /*TODO*/ }) {
                    Text(text = "salam")
                    
                }
            }
        }

        composable(route = BottomBarScreen.About.route) {
            HousifyAboutScreen()
        }
    }
}

