package com.example.housify.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.housify.R

@Composable
fun HousifyAboutScreen(modifier: Modifier = Modifier
    .background(MaterialTheme.colors.background)) {
    Column(
        modifier
            .fillMaxSize()
            .padding(20.dp)

    ) {
        Text(text = stringResource(R.string.about),
        style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(R.string.lorem),
        style = MaterialTheme.typography.body2)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = stringResource(R.string.design),
        style = MaterialTheme.typography.h1)
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(painter = painterResource(R.drawable.dtt_banner),
                contentDescription = "DTTBanner",
                modifier
                    .width(120.dp)
                    .height(40.dp)
                    
            )
            Spacer(modifier = Modifier.width(20.dp))
            Column {
                Text(text = "by DTT", style = MaterialTheme.typography.caption)
                Text(text = "d-tt.nl", style = MaterialTheme.typography.caption,
                    color = Color.Blue
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