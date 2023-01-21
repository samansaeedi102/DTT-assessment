package com.example.housify.ui.screens

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import com.example.housify.R
import com.example.housify.navigation.MainScreens
import com.example.housify.ui.theme.HousifyTheme
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    var startAnimation by remember { mutableStateOf(false)}
    val alphaAnim = animateFloatAsState(
        targetValue = if (startAnimation) 1f else 0f,
        animationSpec = tween(
            durationMillis = 1500
        )
    )
    LaunchedEffect(key1 = true) {
        startAnimation = true
        delay(1500)
        navController.popBackStack()
        navController.navigate(MainScreens.Home.route)
    }
    Splash(alpha = alphaAnim.value)

}

@Composable
fun Splash(alpha: Float) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(painter = painterResource(id = R.drawable.dtt_splash),
            contentDescription = null,
            modifier = Modifier
                .alpha(alpha)
                .fillMaxWidth()
        )
    }
}
@Preview
@Composable
fun SplashScreenPreview() {
    HousifyTheme {
        Splash(alpha = 1f)
    }

}