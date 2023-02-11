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
import com.example.housify.R
import com.example.housify.utils.HyperlinkText
import com.example.housify.utils.spacing

/**
 * Shows DTT company information.
 */
@Composable
fun HousifyAboutScreen(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxSize()
            .padding(
                start = MaterialTheme.spacing.startPadding,
                top = MaterialTheme.spacing.topPadding,
                end = MaterialTheme.spacing.endPadding,
                bottom = MaterialTheme.spacing.bottomPadding
            )
            .background(MaterialTheme.colors.background)
    ) {
        Text(
            text = stringResource(R.string.about),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Text(
            text = stringResource(R.string.lorem),
            style = MaterialTheme.typography.body1, color = MaterialTheme.colors.onSurface
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        Text(
            text = stringResource(R.string.design),
            style = MaterialTheme.typography.h1
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.large))
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.spacing.extraSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.dtt_banner),
                contentDescription = stringResource(R.string.DTT_banner),
                modifier
                    .width(MaterialTheme.spacing.bannerWidth)
                    .height(MaterialTheme.spacing.extraLarge)
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.large))
            Column {
                Text(text = stringResource(R.string.by_DTT), style = MaterialTheme.typography.caption)
                HyperlinkText(
                    fullText = stringResource(R.string.dtt_hyperlink_text), linkText = listOf(stringResource(R.string.dtt_hyperlink_text)),
                    hyperlinks = listOf(stringResource(R.string.dtt_url))
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