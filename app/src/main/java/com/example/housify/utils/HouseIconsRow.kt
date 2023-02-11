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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import com.example.housify.R

/**
 * Prepare icons and texts of house details and can be implemented in other screens.
 */
@Composable
fun HouseIconsRow(
    bedrooms: Int, bathrooms: Int, size: Int, distance: Int
) {
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_bed),
        contentDescription = stringResource(R.string.bedrooms),
        modifier = Modifier.size(MaterialTheme.spacing.extraSmall)
    )
    Text(
        text = " $bedrooms",
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle2
    )
    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_bath),
        contentDescription = stringResource(R.string.bathrooms),
        modifier = Modifier.size(MaterialTheme.spacing.extraSmall)
    )
    Text(
        text = " $bathrooms",
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle2
    )
    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_layers),
        contentDescription = stringResource(R.string.size_of_the_house),
        modifier = Modifier.size(MaterialTheme.spacing.extraSmall)
    )
    Text(
        text = " $size",
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle2
    )
    Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
    Icon(
        imageVector = ImageVector.vectorResource(R.drawable.ic_location),
        contentDescription = stringResource(R.string.distance_of_the_house),
        modifier = Modifier.size(MaterialTheme.spacing.extraSmall)
    )
    Text(
        text = " $distance km",
        color = MaterialTheme.colors.onSurface,
        style = MaterialTheme.typography.subtitle2
    )
}