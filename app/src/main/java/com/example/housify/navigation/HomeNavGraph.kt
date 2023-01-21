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
import java.util.*

//The graph where it is checked if internet is connected and data is fetched from API. If internet is connected
//data will be loaded to the screen
@Composable
fun HomeNavGraph(
    navController: NavHostController,
    screenNavController: NavController,
    housifyUiState: HousifyUiState,
    viewModel: HousifyViewModel
) {
    //This is false at the beginning nd gets true only if the user tends to search
    var isSearchScreen by remember { mutableStateOf(false) }
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            //If the user clicks search button SearchScreen will be shown
            if (isSearchScreen) {
                HousifySearchScreen(onCloseClick = { isSearchScreen = false })
            } else {
                //Loads data from API on start up
                when (housifyUiState) {
                    //If data is successfully fetched, the columns will show the data
                    is HousifyUiState.Success -> HousifyHomeScreenContent(housifyHouses = housifyUiState.houses,
                        //If user enters a string in search text field, and the house exists,
                        // will navigate to details of the house
                        onSearchClick = { term ->
                            housifyUiState.houses.forEach { it ->
                                if (it.city == term.replaceFirstChar {
                                        if (it.isLowerCase()) it.titlecase(
                                            Locale.getDefault()
                                        ) else it.toString()
                                    } || it.zip == term.uppercase()) {
                                    viewModel.selectedHouseChanged(it)
                                    screenNavController.popBackStack()
                                    screenNavController.navigate(MainScreens.Details.route)
                                }
                            }
                            //If search term does not correspond, search screen with empty result will be shown
                            isSearchScreen = true
                        },
                        //Navigate to the screen of chosen house
                        onHouseClick = {
                            viewModel.selectedHouseChanged(it)
                            screenNavController.popBackStack()
                            screenNavController.navigate(MainScreens.Details.route)
                        })
                    //Show error screen in case internet is disconnected
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

