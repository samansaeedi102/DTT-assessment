package com.example.housify.ui


import android.location.Location
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.map.showDistance
import com.example.housify.network.HousifyHouse
import com.google.android.gms.maps.model.LatLng


@Composable
fun HousifyHomeScreenContent (
    housifyHouses: List<HousifyHouse>,
    onSearchClick: () -> Unit,
    onHouseClick: (HousifyHouse) -> Unit
){
    var searchTerm by remember { mutableStateOf("")}
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp),
    ) {
        Text(text = stringResource(id = R.string.home_header), style = MaterialTheme.typography.h1)
        Spacer(modifier = Modifier.height(20.dp))
        SearchTextField(
            value = searchTerm,
            onSearchClick = onSearchClick,
            onValueChange = {searchTerm = it},
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = {onSearchClick()}
            ),
            icon = ImageVector.vectorResource(R.drawable.ic_search)
        )
        Spacer(modifier = Modifier.height(10.dp))
        HousesColumn(houses = housifyHouses,onHouseClick )
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseCard(house: HousifyHouse, onHouseClick: (HousifyHouse)->Unit){
    var houseLocation = Location("Place")
    houseLocation.latitude= house.latitude.toDouble()
    houseLocation.longitude = house.longitude.toDouble()
    var distance = showDistance(houseLocation, LocalContext.current)
    Card(elevation = 4.dp, onClick = {onHouseClick(house)}, modifier = Modifier.padding(0.dp,9.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(stringResource(id = R.string.house_image_api, house.image))
                .crossfade(true)
                .build(),
                contentDescription = stringResource(id = R.string.house_image_home),
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp)))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = stringResource(id = R.string.house_price, "%,d".format(house.price)) , style = MaterialTheme.typography.h1)
                Text(text = house.zip, color = MaterialTheme.colors.onSurface)
                Spacer(modifier = Modifier.height(25.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bed), contentDescription = "bedroom")
                    Text(text = "${house.bedrooms}", color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bath), contentDescription = "bathroom")
                    Text(text = "${house.bathrooms}", color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_layers), contentDescription = "size")
                    Text(text = "${house.size}", color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.width(16.dp))
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "bed")
                    Text(text = "$distance", color = MaterialTheme.colors.onSurface)
                }
            }
        }
    }
}

@Composable
fun SearchTextField(value: String,
                    onSearchClick: () -> Unit,
                    onValueChange: (String) -> Unit,
                    keyboardOptions: KeyboardOptions,
                    keyboardActions: KeyboardActions,
                    icon: ImageVector
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        trailingIcon = {
            IconButton(onClick = { onSearchClick()}) {
                Icon(imageVector = icon, contentDescription = "Close")
            }
        },
        placeholder = { Text(text = "Search for a home", style = MaterialTheme.typography.body1)},
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp)),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            disabledTextColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun HousesColumn(houses: List<HousifyHouse>, onClick: (HousifyHouse) -> Unit) {
    LazyColumn {
        items(houses, key = {house -> house.id}) { house->
            HouseCard(house = house, onHouseClick = onClick)
        }
    }
}
@Composable
fun ErrorScreen() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        Image(painter = painterResource(id = R.drawable.disconnected),
            contentDescription = stringResource(
            id = R.string.disconnected
        ))
        Text("You are not connected to Internet")
    }
}