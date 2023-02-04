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
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.ui.screens.map.MapScreen
import com.example.housify.ui.screens.map.showDistance
import com.example.housify.data.network.HousifyHouse
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.utils.HouseIconsRow
import com.google.android.gms.maps.model.LatLng

@Composable
fun HousifyDetailsScreen(viewModel: HousifyViewModel, onBackClick: ()-> Unit) {
    BackHandler {
        onBackClick()
    }
    val house = viewModel.selectedHouse
    val scroll: ScrollState = rememberScrollState(0)
    if (house != null) {
        CollapsingToolbar(house = house, scroll = scroll, onBackClick = { onBackClick() })
    }


}
@Composable
fun DetailsImage(house: HousifyHouse) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(stringResource(id = R.string.house_image_api, house.image))
            .crossfade(true)
            .build(),
        modifier = Modifier.fillMaxSize(),
        contentDescription = "house",
        contentScale = ContentScale.Crop)
}

@Composable
fun DetailsCard(house: HousifyHouse) {
    val houseLocation = Location("Place")
    houseLocation.latitude= house.latitude.toDouble()
    houseLocation.longitude = house.longitude.toDouble()
    val distance = showDistance(houseLocation, LocalContext.current)
    Card(
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp)) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(id = R.string.house_price, "%,d".format(house.price)), style = MaterialTheme.typography.h1)
                Spacer(modifier = Modifier.width(25.dp))
                HouseIconsRow(house.bedrooms,house.bathrooms,house.size,distance)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Description", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = house.description, color = MaterialTheme.colors.onSurface)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Location", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(10.dp))
            val location = LatLng(house.latitude.toDouble(),house.longitude.toDouble())
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .wrapContentHeight(Alignment.Bottom)){
                MapScreen(location){}
            }
        }
    }
}
@Composable
fun CollapsingToolbar(
    house: HousifyHouse,
    scroll: ScrollState,
    onBackClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Header(house)
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_back), contentDescription = "back",
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp)
                .clickable { onBackClick() },
            tint = Color.White)
        Body(house,scroll)

    }
}
private val headerHeight = 255.dp
@Composable
private fun Header(house: HousifyHouse) {
    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(headerHeight)) {
        DetailsImage(house = house)
    }
}

@Composable
private fun Body(house: HousifyHouse, scroll: ScrollState) {
    Column(horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.verticalScroll(scroll)) {

        Spacer(Modifier.height(headerHeight))

        DetailsCard(house = house)
    }
}



