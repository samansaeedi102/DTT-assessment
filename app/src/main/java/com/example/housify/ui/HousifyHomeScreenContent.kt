package com.example.housify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.housify.R
import com.example.housify.navigation.MainScreens
import com.example.housify.ui.screens.HousifyUiState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HousifyHomeScreenContent (housifyUiState: String, onButtonClick: () -> Unit, onSearchClick: () -> Unit){
    var searchValue by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp, 30.dp),
    ) {
        Text(text = "DTT REAL ESTATE", style = MaterialTheme.typography.h1)
        Text(text = housifyUiState)
        TextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            trailingIcon = {
                IconButton(onClick = { onSearchClick()}) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_search), contentDescription = "Close")
                }
            },
            placeholder = { Text(text = "Search for a home")},
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        CardComposable(onClick = {onButtonClick()})
        CardComposable(onClick = {onButtonClick()})
        CardComposable(onClick = {onButtonClick()})
        CardComposable(onClick = {onButtonClick()})
        CardComposable(onClick = {onButtonClick()})
        CardComposable(onClick = {onButtonClick()})

    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardComposable(onClick: ()->Unit){
    Card(elevation = 4.dp, onClick = {onClick()}, modifier = Modifier.padding(0.dp,5.dp)) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(painter = painterResource(R.drawable.dtt_banner), contentDescription = null,Modifier.size(90.dp))
            Column {
                Text(text = "$45000")
                Text(text = "1011KH")
                Spacer(modifier = Modifier.height(10.dp))
                Row() {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bed), contentDescription = "bed")
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bath), contentDescription = "bed")
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_layers), contentDescription = "bed")
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "bed")
                }
            }
        }
    }
}

@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("Falied to load")
    }
}