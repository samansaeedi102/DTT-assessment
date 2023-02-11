package com.example.housify.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.housify.R
import com.example.housify.data.network.HousifyHouse
import com.example.housify.ui.screens.HousifyViewModel
import com.example.housify.ui.screens.map.MapScreen
import com.example.housify.utils.HouseIconsRow
import com.example.housify.utils.spacing
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
    if (house != null) {
        CollapsingToolBar(house = house, onBackClick = onBackClick)
    }
}


/**
 * Provides container for all the details of selected house.
 */
@Composable
fun DetailsCard(house: HousifyHouse) {
    Card(
        shape = RoundedCornerShape(
            topStart = MaterialTheme.spacing.cardRoundedCorner,
            topEnd = MaterialTheme.spacing.cardRoundedCorner
        ),
        modifier = Modifier
            .fillMaxHeight()
            .offset(y = MaterialTheme.spacing.minusOffset),
        elevation = MaterialTheme.spacing.default
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(
                    start = MaterialTheme.spacing.startPadding,
                    top = MaterialTheme.spacing.topPadding,
                    end = MaterialTheme.spacing.endPadding,
                    bottom = MaterialTheme.spacing.bottomPadding
                )
        ) {
            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = stringResource(id = R.string.house_price, "%,d".format(house.price)),
                    style = MaterialTheme.typography.h1
                )
                Spacer(modifier = Modifier.width(MaterialTheme.spacing.extraLarge))
                HouseIconsRow(house.bedrooms, house.bathrooms, house.size, house.distance)
            }
            Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
            Text(text = stringResource(R.string.description), style = MaterialTheme.typography.h1)

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xxSmall))
            Text(
                text = house.description,
                color = MaterialTheme.colors.onSurface,
                style = MaterialTheme.typography.body1
            )

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xxxSmall))
            Text(text = stringResource(R.string.location), style = MaterialTheme.typography.h1)

            Spacer(modifier = Modifier.height(MaterialTheme.spacing.xxxSmall))
            val location = LatLng(house.latitude.toDouble(), house.longitude.toDouble())
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(MaterialTheme.spacing.xxxLarge)
                    .wrapContentHeight(Alignment.Bottom)
            ) {
                MapScreen(location) {}
            }
        }
    }
}


@Composable
fun CollapsingToolBar(
    house: HousifyHouse,
    onBackClick: () -> Unit
) {
    val lazyListState = rememberLazyListState()
    var scrolledY = 0f
    var previousOffset = 0
    Scaffold(
        content = {
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colors.surface),
                lazyListState,
            ) {
                item {
                    Box {
                        AsyncImage(
                            model = ImageRequest.Builder(context = LocalContext.current)
                                .data(stringResource(id = R.string.house_image_api, house.image))
                                .crossfade(true)
                                .build(),
                            modifier = Modifier
                                .fillMaxSize()
                                .graphicsLayer {
                                    scrolledY += lazyListState.firstVisibleItemScrollOffset - previousOffset
                                    translationY = scrolledY * 0.5f
                                    previousOffset = lazyListState.firstVisibleItemScrollOffset
                                }
                                .height(MaterialTheme.spacing.xxxLarge)
                                .fillMaxWidth(),
                            contentDescription = stringResource(R.string.selected_house),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
                item {
                    Column(Modifier.height(MaterialTheme.spacing.xxixLarge)) {
                        DetailsCard(house = house)
                    }

                }
            }
        },
        floatingActionButton = {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                contentDescription = stringResource(R.string.back),
                modifier = Modifier
                    .offset(
                        x = MaterialTheme.spacing.floatingButtonMinusWidth,
                        y = MaterialTheme.spacing.floatingButtonMinusHeight
                    )
                    .clickable { onBackClick() },
                tint = Color.White
            )
        },
        floatingActionButtonPosition = FabPosition.Center
    )
}



