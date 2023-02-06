package com.example.housify.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housify.R
import com.example.housify.utils.HyperlinkText

/**
 * Shows DTT company information.
 */
@Composable
fun HousifyAboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp)
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(R.string.lorem),
            style = MaterialTheme.typography.body2, color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(R.string.design),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dtt_banner),
                contentDescription = stringResource(R.string.DTT_banner),
                modifier
                    .width(120.dp)
                    .height(40.dp)
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = stringResource(R.string.by_DTT), style = MaterialTheme.typography.caption)
                HyperlinkText(
                    fullText = "d-tt.nl", linkText = listOf("d-tt.nl"),
                    hyperlinks = listOf("https://www.d-tt.nl/")
                )
            }
        }
    }
}


@Preview
@Composable
fun HousifyAboutScreenPreview() {
    HousifyTheme {
        HousifyAboutScreen()
    }
}