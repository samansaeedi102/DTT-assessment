package com.example.housify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.outlined.Close
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.housify.R


@Composable
fun HousifySearchScreen(onCloseClick: () -> Unit) {
    var searchTerm by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.Top,

    ) {
        Row(Modifier.wrapContentWidth(Alignment.Start)) {
            Text(text = stringResource(id = R.string.home_header), style = MaterialTheme.typography.h1)
        }
        Spacer(modifier = Modifier.height(20.dp))
        SearchTextField(
            value = searchTerm,
            onSearchClick = onCloseClick,
            onValueChange = {searchTerm = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onSearch = {onCloseClick}
            ),
            icon = ImageVector.vectorResource(id = R.drawable.ic_close)
        )
        Image(
            painter = painterResource(R.drawable.search_state_empty),
            contentDescription = "search empty",
            modifier = Modifier.size(350.dp).padding(top = 150.dp),
        )
    }
}
