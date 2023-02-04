package com.example.housify.utils

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.housify.R

@Composable
fun HouseIconsRow(
    bedrooms: Int, bathrooms: Int, size: Int, distance: Int
) {
    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bed), contentDescription = "bedroom", modifier = Modifier.size(18.dp))
    Text(text = " $bedrooms", color = MaterialTheme.colors.onSurface)
    Spacer(modifier = Modifier.width(12.dp))
    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bath), contentDescription = "bathroom", modifier = Modifier.size(18.dp))
    Text(text = " $bathrooms", color = MaterialTheme.colors.onSurface)
    Spacer(modifier = Modifier.width(12.dp))
    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_layers), contentDescription = "size", modifier = Modifier.size(18.dp))
    Text(text = " $size", color = MaterialTheme.colors.onSurface)
    Spacer(modifier = Modifier.width(7.dp))
    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "bed", modifier = Modifier.size(18.dp))
    Text(text = " $distance km", color = MaterialTheme.colors.onSurface)
}