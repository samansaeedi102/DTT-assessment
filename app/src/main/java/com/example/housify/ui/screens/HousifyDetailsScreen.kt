package com.example.housify.ui

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.map.MapScreen
import com.example.housify.map.showDistance
import com.example.housify.network.HousifyHouse
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.utils.HouseIconsRow
import com.google.android.gms.maps.model.LatLng

@Composable
fun HousifyDetailsScreen(viewModel: HousifyViewModel, onBackClick: ()-> Unit) {
    Box() {
        val house = viewModel.selectedHouse
        if (house != null) {
            DetailsImage(house = house ,modifier = Modifier.align(Alignment.TopCenter))
        }
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_back), contentDescription = "back",
            modifier = Modifier
                .padding(start = 20.dp, top = 30.dp)
                .clickable(onClick = { onBackClick() }),
                tint = Color.White)
        if (house != null) {
            DetailsCard(house = house)
        }
    }


}
@Composable
fun DetailsImage(house: HousifyHouse, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(stringResource(id = R.string.house_image_api, house.image))
            .crossfade(true)
            .build(),
        contentDescription = "house")
}

@Composable
fun DetailsCard(house: HousifyHouse) {
    var houseLocation = Location("Place")
    houseLocation.latitude= house.latitude.toDouble()
    houseLocation.longitude = house.longitude.toDouble()
    var distance = showDistance(houseLocation, LocalContext.current)
    Card(modifier = Modifier.padding(start = 5.dp, top = 200.dp, end = 5.dp),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ){
        Column(
            Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp)) {
            Row(Modifier.fillMaxWidth()) {
                Text(text = stringResource(id = R.string.house_price, "%,d".format(house.price)), style = MaterialTheme.typography.h1)
                Spacer(modifier = Modifier.width(25.dp))
                HouseIconsRow(house.bedrooms,house.bathrooms,house.size,distance)
            }
            Spacer(modifier = Modifier.height(15.dp))
            Text(text = "Description", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = house.description)
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = "Location", style = MaterialTheme.typography.h1)
            Spacer(modifier = Modifier.height(10.dp))
            val location = LatLng(house.latitude.toDouble(),house.longitude.toDouble())
            Box(modifier = Modifier.fillMaxWidth().wrapContentHeight(Alignment.Bottom)){
                MapScreen(location){}
            }
        }
    }
}
