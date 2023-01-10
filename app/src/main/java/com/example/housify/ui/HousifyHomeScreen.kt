package com.example.housify.ui


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.BottomBarScreen
import com.example.housify.navigation.HomeNavGraph
import com.example.housify.navigation.Screen


@Composable
fun HousifyHomeScreen(navController: NavHostController = rememberNavController(), navRootController: NavController) {
    Scaffold(
        bottomBar = {  BottomBar(navController = navController) }
    ) {
        HomeNavGraph(navController = navController)
        Button(onClick = {navRootController.navigate(Screen.Details.route) }) {
            Text(text = "by")
        }
    }
}
@Composable
fun SearchContent(name: String, onClick: () -> Unit) {
    Box(
        modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = name,
            fontSize = MaterialTheme.typography.h3.fontSize,
            fontWeight = FontWeight.Bold
        )
        Button(onClick = {onClick() }) {

        }
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
