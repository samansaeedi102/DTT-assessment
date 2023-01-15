package com.example.housify.ui


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.housify.network.HousifyHouse


@Composable
fun HousifyHomeScreenContent (
    housifyHouses: List<HousifyHouse>,
    onSearchClick: () -> Unit,
    onHouseClick: (HousifyHouse) -> Unit
){
    var searchValue by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(start = 30.dp, top = 10.dp, end = 30.dp, bottom = 50.dp),
    ) {
        Text(text = "DTT REAL ESTATE", style = MaterialTheme.typography.h1)
        TextField(
            value = searchValue,
            onValueChange = { searchValue = it },
            trailingIcon = {
                IconButton(onClick = { onSearchClick()}) {
                    Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_search), contentDescription = "Close")
                }
            },
            placeholder = { Text(text = "Search for a home", style = MaterialTheme.typography.body1)},
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        HousesColumn(houses = housifyHouses,onHouseClick )


    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CardComposable(house: HousifyHouse, onHouseClick: (HousifyHouse)->Unit){
    Card(elevation = 4.dp, onClick = {onHouseClick(house)}, modifier = Modifier.padding(0.dp,9.dp)) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)) {
            AsyncImage(model = ImageRequest.Builder(context = LocalContext.current)
                .data(stringResource(id = R.string.image, house.image))
                .crossfade(true)
                .build(),
                contentDescription = "khune",
                contentScale= ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp)))
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(text = "${house.price}", style = MaterialTheme.typography.h1)
                Text(text = house.zip, color = MaterialTheme.colors.onSurface)
                Spacer(modifier = Modifier.height(25.dp))
                Row() {
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
                    Text(text = "${house.latitude}", color = MaterialTheme.colors.onSurface)
                }
            }
        }
    }
}

@Composable
fun HousesColumn(houses: List<HousifyHouse>, onClick: (HousifyHouse) -> Unit) {
    LazyColumn {
        items(houses, key = {house -> house.id}) { house->
            CardComposable(house = house, onHouseClick = onClick)
        }
    }
}
@Composable
fun ErrorScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Text("You are not connected to Internet")
    }
}