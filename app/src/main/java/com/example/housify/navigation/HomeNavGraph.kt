package com.example.housify.navigation

import androidx.compose.foundation.clickable
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
import com.example.housify.ui.HousifySearchScreen
import com.example.housify.ui.theme.HousifyAboutScreen

@Composable
fun HomeNavGraph(navController: NavHostController,navRootController: NavController) {
    var searchValue by remember { mutableStateOf("") }
    var isSearchPage by remember { mutableStateOf(false)}
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if (isSearchPage) {
                HousifySearchScreen(onClick = { isSearchPage = false })

            } else {
                    TextField(value = searchValue, onValueChange = { searchValue = it },
                        trailingIcon = {
                            IconButton(onClick = { isSearchPage = true }) {
                                Icon(imageVector = Icons.Default.Search, contentDescription = "Close")
                            }
                        }, label = { Text(text = "Search for a home")})



                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Button(onClick = { navRootController.navigate(Screen.Details.route) }) {
                        Text(text = "go to details ")
                    }

                }
            }
        }

        composable(route = BottomBarScreen.About.route) {
            HousifyAboutScreen()
        }
    }
}

