package com.example.housify.navigation

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.housify.ui.SearchContent
import com.example.housify.ui.theme.HousifyAboutScreen

@Composable
fun HomeNavGraph(navController: NavHostController) {
    var isSearchPage by remember { mutableStateOf(false)}
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ) {
        composable(route = BottomBarScreen.Home.route) {
            if(isSearchPage){
                SearchContent(name = "salam", onClick=  {isSearchPage = false })

            } else{
                Button(onClick =  {isSearchPage = true}) {
                    Text(text = "bezan roosh")
                }
            }
        }
        composable(route = BottomBarScreen.About.route) {
            HousifyAboutScreen()
        }
    }
}

