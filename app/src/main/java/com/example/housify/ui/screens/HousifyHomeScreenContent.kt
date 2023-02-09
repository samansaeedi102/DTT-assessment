package com.example.housify.ui


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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.data.network.HousifyHouse
import com.example.housify.navigation.MainScreens
import com.example.housify.ui.screens.ErrorScreen
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.utils.HouseIconsRow
import com.example.housify.utils.SearchTextField

/**
 * Provides all the content needed for the home page.
 */
@Composable
fun HousifyHomeScreenContent(
    viewModel: HousifyViewModel = hiltViewModel(),
    screenNavController: NavController
) {
    val searchTextFieldFocusRequester = remember { FocusRequester() }
    val uiState by viewModel.uiState.collectAsState()
    if(uiState.searchUnsuccessful) {
        LaunchedEffect(Unit) {
            searchTextFieldFocusRequester.requestFocus()
        }
    }

    //Appears if user's search result does not match any of the houses.
//    if (uiState.searchUnsuccessful) {
//
//    } else {

        //Appears if the user's search matches at least one of the houses
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 30.dp, top = 40.dp, end = 30.dp, bottom = 50.dp),
        ) {
            Text(
                text = stringResource(id = R.string.home_header),
                style = MaterialTheme.typography.h1
            )
            Spacer(modifier = Modifier.height(20.dp))
            SearchTextField(
                value = uiState.currentSearchedTerm,
                onCloseClick = {
                    viewModel.closeEmptySearchScreen()
                    viewModel.deleteSearchedTerm()
                },
                onValueChange = {
                    viewModel.updateSearchedTerm(it)
                    viewModel.searchHouse(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = {}
                ),
                icon = if(uiState.searchUnsuccessful) {
                    ImageVector.vectorResource(R.drawable.ic_close)
                } else {
                    ImageVector.vectorResource(R.drawable.ic_search)
                },
                focusRequester = searchTextFieldFocusRequester,
                placeholder = "Search for a home"
            )
            Spacer(modifier = Modifier.height(10.dp))

            //Loads error screen if user has no access to internet.
            if (uiState.noInternet) {
                ErrorScreen()
            }
            //Loads houses list if the user has access to internet.
            if(uiState.searchUnsuccessful) {
                HousifySearchScreen()
            }
            HousesColumn(
                houses = viewModel.housesList,
                onHouseClick = {
                    viewModel.selectedHouseChanged(it)
                    viewModel.deleteSearchedTerm()
                    viewModel.closeEmptySearchScreen()
                    screenNavController.popBackStack()
                    screenNavController.navigate(MainScreens.Details.route)
                }
            )
        }

}

/**
 * Describes all houses' data in a clickable card.
 */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HouseCard(
    house: HousifyHouse,
    onHouseClick: (HousifyHouse) -> Unit,
) {
    Card(
        elevation = 4.dp,
        onClick = { onHouseClick(house) },
        modifier = Modifier.padding(0.dp, 9.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context = LocalContext.current)
                    .data(stringResource(id = R.string.house_image_api, house.image))
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.house_image_home),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(90.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column {
                Text(
                    text = stringResource(id = R.string.house_price, "%,d".format(house.price)),
                    style = MaterialTheme.typography.h1
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = house.zip, color = MaterialTheme.colors.onSurface)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = house.city, color = MaterialTheme.colors.onSurface)
                }
                Spacer(modifier = Modifier.height(25.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    HouseIconsRow(house.bedrooms, house.bathrooms, house.size, house.distance)
                }
            }
        }
    }
}

/**
 * Provides column to contain all the houses inside.
 */
@Composable
fun HousesColumn(
    houses: List<HousifyHouse>,
    onHouseClick: (HousifyHouse) -> Unit
) {
    LazyColumn {
        items(houses, key = { house -> house.id }) {
            HouseCard(house = it, onHouseClick = onHouseClick)
        }
    }
}
