package com.example.housify.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.SearchContent
import com.example.housify.ui.theme.HousifyAboutScreen

@Composable
fun HomeNavGraph(navController: NavHostController,navRootController: NavController) {
    var isSearchPage by remember { mutableStateOf(false)}
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if(isSearchPage){
                SearchContent(name = "dfksfljsdflkjdslfsalam", onClick=  {isSearchPage = false })

            } else{
                Button(onClick =  {isSearchPage = true}) {
                    Text(text = "Search")
                }
                Column(modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally) {
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

