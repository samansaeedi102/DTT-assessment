package com.example.housify

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.example.housify.navigation.ScreenNavGraph
import com.example.housify.ui.theme.HousifyTheme

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HousifyTheme {
                ScreenNavGraph(navController = rememberNavController())
            }
        }
    }
}
