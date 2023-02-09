package com.example.housify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.housify.R

/**
 * Appears if the user's search doesn't have a result.
 */
@Composable
fun HousifySearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp),
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(R.drawable.search_state_empty),
            contentDescription = stringResource(R.string.unsuccessful_search),
            modifier = Modifier
                .size(350.dp)
                .padding(top = 150.dp),
        )
        Spacer(modifier = Modifier.height(45.dp))
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = stringResource(id = R.string.no_results_found),
                color = MaterialTheme.colors.onSurface
            )
            Text(
                text = stringResource(id = R.string.another_search),
                color = MaterialTheme.colors.onSurface
            )
        }
    }
}
