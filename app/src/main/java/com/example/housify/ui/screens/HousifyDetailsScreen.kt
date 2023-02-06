package com.example.housify.ui

import android.location.Location
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.data.network.HousifyHouse
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.screens.map.MapScreen
import com.example.housify.utils.HouseIconsRow
import com.google.android.gms.maps.model.LatLng

/**
 * Shows details of selected house.
 */
@Composable
fun HousifyDetailsScreen(viewModel: HousifyViewModel, onBackClick: () -> Unit) {
    BackHandler {
        onBackClick()
    }
    val house = viewModel.selectedHouse
    val scroll: ScrollState = rememberScrollState(0)
    if (house != null) {
        CollapsingToolbar(house = house, scroll = scroll, onBackClick = onBackClick)
    }
}

/**
 * Loads selected house image.
 */
@Composable
fun DetailsImage(house: HousifyHouse) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(stringResource(id = R.string.house_image_api, house.image))
            .crossfade(true)
            .build(),
        modifier = Modifier.fillMaxSize(),
        contentDescription = stringResource(R.string.selected_house),
        contentScale = ContentScale.Crop
    )
}

/**
 * Provides container for all the details of selected house.
 */
@Composable
fun DetailsCard(house: HousifyHouse) {
    val houseLocation = Location("Place")
    houseLocation.latitude = house.latitude.toDouble()
    houseLocation.longitude = house.longitude.toDouble()
    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        modifier = Modifier
            .border((-1).dp, color = Color.Transparent)
            .fillMaxHeight()
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 30.dp, end = 30.dp, bottom = 50.dp)
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.house_price, "%,d".format(house.price)),
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.width(45.dp))
                HouseIconsRow(house.bedrooms, house.bathrooms, house.size, house.distance)
            }
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = stringResource(R.string.description), style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(13.dp))
            Text(text = house.description, color = MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = stringResource(R.string.location), style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(10.dp))
            val location = LatLng(house.latitude.toDouble(), house.longitude.toDouble())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
                    .wrapContentHeight(Alignment.Bottom)
            ) {
                MapScreen(location) {}
            }
        }
    }
}

/**
 * Minimizes image in case of scrolling details of the house.
 */
@Composable
fun CollapsingToolbar(
    house: HousifyHouse,
    scroll: ScrollState,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colors.background)) {
        Header(house)
        Cover(house, scroll = scroll)
        Body(house, scroll)
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
            contentDescription = stringResource(R.string.back),
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp)
                .clickable { onBackClick() },
            tint = Color.White
        )
    }
}

/**
 * Provides height from the top.
 */
val headerHeight = 255.dp

/**
 * Shows the image in the header of collapsing toolbar
 */
@Composable
private fun Header(house: HousifyHouse) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(headerHeight)
    ) {
        DetailsImage(house = house)
    }
}

/**
 * Shows selected house details in bottom of collapsing toolbar
 */
@Composable
private fun Body(house: HousifyHouse, scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scroll)
    ) {
        Spacer(Modifier.height(headerHeight))
        DetailsCard(house = house)
    }
}

/**
 * Creates a cover on the header of collapsing toolbar in order to meet design requirements.
 */
@Composable
private fun Cover(house: HousifyHouse, scroll: ScrollState) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .verticalScroll(scroll)
            .offset(y = (-15).dp)
    ) {
        Spacer(Modifier.height(headerHeight))
        DetailsCard(house = house)
    }
}



