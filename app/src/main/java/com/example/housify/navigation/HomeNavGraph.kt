package com.example.housify.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.ErrorScreen
import com.example.housify.ui.HousifyHomeScreenContent
import com.example.housify.ui.HousifySearchScreen
import com.example.housify.ui.screens.HousifyUiState
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.theme.HousifyAboutScreen

@Composable
fun HomeNavGraph(navController: NavHostController,
                 screenNavController: NavController,
                 housifyUiState: HousifyUiState,
                 viewModel: HousifyViewModel
) {
    var isSearchScreen by remember { mutableStateOf(false)}
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if (isSearchScreen) {
                HousifySearchScreen{isSearchScreen = false}
            } else {
                when(housifyUiState) {
                    is HousifyUiState.Success -> HousifyHomeScreenContent(housifyHouses = housifyUiState.houses,
                        onSearchClick = {isSearchScreen = true},
                        onHouseClick = {
                            viewModel.selectedHouseChanged(it)

                            screenNavController.navigate(MainScreens.Details.route)
                        })
                    is HousifyUiState.Error -> ErrorScreen()
                    is HousifyUiState.Loading -> ErrorScreen()

                }
            }
        }

        composable(route = BottomBarScreen.About.route) {
            HousifyAboutScreen()
        }
    }
}

