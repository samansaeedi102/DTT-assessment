package com.example.housify.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.network.HousifyHouse

@Composable
fun HousifyDetailsScreen(house: HousifyHouse, onBackClick: ()-> Unit) {
    Box(modifier = Modifier
        .background(MaterialTheme.colors.background)) {
        Icon(imageVector = ImageVector.vectorResource(id = R.drawable.ic_back), contentDescription = "back",
        modifier = Modifier
            .padding(start = 20.dp, top = 30.dp)
            .clickable(onClick = { onBackClick() }))

        DetailsImage(house = house ,modifier = Modifier.align(Alignment.TopCenter))
        Card(modifier = Modifier.padding(top = 200.dp),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            backgroundColor = Color.Gray
        ){
            Column(Modifier.fillMaxWidth()) {
                Row(Modifier.fillMaxWidth()) {
                    Text(text = "434234", style = MaterialTheme.typography.body2)
                    Row() {
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bed), contentDescription = "bedroom")
                        Text(text = "3", color = MaterialTheme.colors.onSurface)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_bath), contentDescription = "bathroom")
                        Text(text = "4", color = MaterialTheme.colors.onSurface)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_layers), contentDescription = "size")
                        Text(text = "45", color = MaterialTheme.colors.onSurface)
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(imageVector = ImageVector.vectorResource(R.drawable.ic_location), contentDescription = "bed")
                        Text(text = "56", color = MaterialTheme.colors.onSurface)
                    }
                }
                Text(text = "Description", style = MaterialTheme.typography.body2)
                Text(text = stringResource(id =R.string.lorem))
                Text(text = "Location", style = MaterialTheme.typography.body2)
            }
        }
    }


}
@Composable
fun DetailsImage(house: HousifyHouse, modifier: Modifier = Modifier) {
    AsyncImage(
        model = ImageRequest.Builder(context = LocalContext.current)
            .data(stringResource(id = R.string.image, house.image))
            .crossfade(true)
            .build(),
        contentDescription = "house")
}
