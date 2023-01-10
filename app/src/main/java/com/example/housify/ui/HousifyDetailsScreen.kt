package com.example.housify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housify.R

@Composable
fun HousifyDetailsScreen() {
    Box(modifier = Modifier
        .background(MaterialTheme.colors.background)) {
        DetailsImage(modifier = Modifier.align(Alignment.TopCenter))
    }

    Card(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)){

    }
}
@Composable
fun DetailsImage(modifier: Modifier = Modifier) {
    Image(painter = painterResource(R.drawable.dtt_banner),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
            )
}
@Preview
@Composable
fun HousifyDetailsScreenPreview() {
    HousifyDetailsScreen()
}