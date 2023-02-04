package com.example.housify.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.ScreenNavGraph
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.theme.HousifyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HousifyTheme {
                val viewModel = hiltViewModel<HousifyViewModel>()
                ScreenNavGraph(navController = rememberNavController(), viewModel)
            }
        }
    }
}
