package com.example.housify.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.BottomBarScreen
import com.example.housify.navigation.MainScreens
import com.example.housify.ui.screens.ErrorScreen
import com.example.housify.ui.screens.HousifyUiState
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.theme.HousifyAboutScreen
import com.example.housify.utils.BottomBar

//Implementing two navControllers for Screens and BottomNavigation
@Composable
fun HousifyHome(
    viewModel: HousifyViewModel = hiltViewModel(),
    bottomBarNavController: NavHostController = rememberNavController(),
    screenNavController: NavController,
    housifyUiState: HousifyUiState,
) {
    Scaffold(
        bottomBar = {  BottomBar(navController = bottomBarNavController) }
    ) {
        //This is false at the beginning nd gets true only if the user tends to search
        var isSearchScreen by remember { mutableStateOf(false) }
        var searchedTerm by remember { mutableStateOf("") }
        val uiState by viewModel.uiState.collectAsState()
        NavHost(
            navController = bottomBarNavController,
            startDestination = BottomBarScreen.Home.route
        ) {
            composable(route = BottomBarScreen.Home.route) {
                //If the user clicks search button SearchScreen will be shown
//                if (isSearchScreen) {
//                    HousifySearchScreen(
//                        onCloseClick = { isSearchScreen = false },
//                        searchedTerm = searchedTerm
//                    )
//                } else {
                    //Loads data from API on start up
                    when (housifyUiState) {
                        //If data is successfully fetched, the columns will show the data
                        is HousifyUiState.Success -> HousifyHomeScreenContent(housifyHouses = uiState.currentHouseList,
                            //If user enters a string in search text field, and the house exists,
                            // will navigate to details of the house
                            onSearchClick = {
//                                    term ->
//                                housifyUiState.houses.forEach { it ->
//                                    if (it.city == term.replaceFirstChar {
//                                            if (it.isLowerCase()) it.titlecase(
//                                                Locale.getDefault()
//                                            ) else it.toString()
//                                        } || it.zip == term.uppercase()) {
//                                        viewModel.selectedHouseChanged(it)
//                                        screenNavController.popBackStack()
//                                        screenNavController.navigate(MainScreens.Details.route)
//                                    }
//                                }
//                                viewModel.searchHouse(term)
                                //If search term does not correspond, search screen with empty result will be shown
//                                searchedTerm = term
//                                isSearchScreen = true
                            }
                        )
                        //Navigate to the screen of chosen house
                        {
                            viewModel.selectedHouseChanged(it)
                            screenNavController.popBackStack()
                            screenNavController.navigate(MainScreens.Details.route)
                        }

                        //Show error screen in case internet is disconnected
                        is HousifyUiState.Error -> ErrorScreen()
                        is HousifyUiState.Loading -> ErrorScreen()
                    }
                    Log.d(TAG,"house ha ${uiState.currentHouseList.count()}")
                }

            composable(route = BottomBarScreen.About.route) {
                HousifyAboutScreen()
            }
        }
    }

}

