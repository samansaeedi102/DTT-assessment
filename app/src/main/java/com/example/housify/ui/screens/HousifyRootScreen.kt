package com.example.housify.ui


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.BottomBarScreen
import com.example.housify.navigation.HomeNavGraph
import com.example.housify.ui.screens.HousifyViewModel


@Composable
fun HousifyRootScreen(bottomBarNavController: NavHostController = rememberNavController(), screenNavController: NavController) {
    Scaffold(
        bottomBar = {  BottomBar(navController = bottomBarNavController) }
    ) {
        val housifyViewModel: HousifyViewModel = viewModel()
        HomeNavGraph(navController = bottomBarNavController,
            screenNavController = screenNavController,
            housifyUiState = housifyViewModel.housifyUiState)
    }

}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.About
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any {it.route == currentDestination?.route}
    if (bottomBarDestination) {
        BottomNavigation() {
            screens.forEach { screen ->
                AddItem(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}

@Composable
fun RowScope.AddItem(screen: BottomBarScreen,
            currentDestination: NavDestination?,
            navController: NavController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
            contentDescription = "navigation"
        )
               },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.high),
        unselectedContentColor = LocalContentColor.current.copy(alpha = ContentAlpha.disabled),
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        }
    )
}
