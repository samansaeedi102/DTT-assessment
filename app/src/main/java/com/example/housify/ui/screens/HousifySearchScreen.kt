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
import com.example.housify.R
import com.example.housify.utils.spacing

/**
 * Appears if the user's search doesn't have a result.
 */
@Composable
fun HousifySearchScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.spacing.startPadding,
                top = MaterialTheme.spacing.topPadding,
                end = MaterialTheme.spacing.endPadding,
                bottom = MaterialTheme.spacing.bottomPadding
            ),
        verticalArrangement = Arrangement.Top,
    ) {
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Image(
            painter = painterResource(R.drawable.search_state_empty),
            contentDescription = stringResource(R.string.unsuccessful_search),
            modifier = Modifier
                .size(MaterialTheme.spacing.emptySearchImage)
                .padding(top = MaterialTheme.spacing.xxLarge),
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
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
