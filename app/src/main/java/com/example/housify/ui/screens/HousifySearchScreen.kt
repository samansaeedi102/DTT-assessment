package com.example.housify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.housify.R


@Composable
fun HousifySearchScreen(onClick: () -> Unit) {
    var searchValue by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "DTT REAL ESTATE", style = MaterialTheme.typography.h1)
        TextField(value = searchValue, onValueChange = { searchValue = it },
            trailingIcon = {
                IconButton(onClick = onClick) {
                    Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                }
            }
        )
        Image(
            painter = painterResource(R.drawable.search_state_empty),
            contentDescription = "search empty",
            modifier = Modifier.size(200.dp)
        )
    }
}
