package com.example.housify.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.HousifyHomeScreenContent
import com.example.housify.ui.HousifySearchScreen
import com.example.housify.ui.screens.ErrorScreen
import com.example.housify.ui.screens.HousifyUiState
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.theme.HousifyAboutScreen

//The graph where it is checked if internet is connected and data is fetched from API. If internet is connected
//data will be loaded to the screen
@Composable
fun HomeNavGraph(navController: NavHostController,
                 screenNavController: NavController,
                 housifyUiState: HousifyUiState,
                 viewModel: HousifyViewModel
) {
    var isSearchScreen by remember { mutableStateOf(false) }
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if (isSearchScreen) {
                HousifySearchScreen(onCloseClick = {isSearchScreen = false})
            } else {
                when(housifyUiState) {
                    is HousifyUiState.Success -> HousifyHomeScreenContent(housifyHouses = housifyUiState.houses,
                        onSearchClick = { term ->
                            housifyUiState.houses.forEach {
                                if (it.city == term || it.zip == term) {
                                    viewModel.selectedHouseChanged(it)
                                    screenNavController.popBackStack()
                                    screenNavController.navigate(MainScreens.Details.route)
                                }
                            }
                            isSearchScreen = true
                        },
                        onHouseClick = {
                            viewModel.selectedHouseChanged(it)
                            screenNavController.popBackStack()
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

