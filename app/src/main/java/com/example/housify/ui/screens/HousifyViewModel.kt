package com.example.housify.ui.screens

import android.content.ContentValues.TAG
import android.content.Context
import android.location.Location
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housify.data.HousifyRepository
import com.example.housify.data.network.HousifyHouse
import com.example.housify.ui.screens.map.checkPermission
import com.example.housify.ui.screens.map.currentLocation
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.io.IOException
import java.util.*
import javax.inject.Inject


sealed interface HousifyUiState {
    data class Success(val houses: List<HousifyHouse>) : HousifyUiState
    object Error: HousifyUiState
    object Loading: HousifyUiState
}

data class UiState(
    val currentHouseList: List<HousifyHouse> = emptyList(),
    var loadError: MutableState<String> = mutableStateOf(""),
    var isLoading: MutableState<Boolean> = mutableStateOf(false)
)

@HiltViewModel
class HousifyViewModel @Inject constructor(private val housifyRepository: HousifyRepository): ViewModel() {
    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()
    var housifyUiState: HousifyUiState by mutableStateOf(HousifyUiState.Loading)
        private set
    var selectedHouse by mutableStateOf<HousifyHouse?>(null)
        private set
    fun selectedHouseChanged(newHouse: HousifyHouse) {
        selectedHouse = newHouse
    }
    init {
        getHouses()
    }

    private fun getHouses() {
        viewModelScope.launch {
            _uiState.update {currentState ->
                currentState.copy(currentHouseList = housifyRepository.getHousesDetails())
            }
            housifyUiState = try {
                HousifyUiState.Success(_uiState.value.currentHouseList)
            } catch (e: IOException) {
                HousifyUiState.Error
            }
        }
    }

    fun searchHouse(term: String) {
    viewModelScope.launch(Dispatchers.Default) {
        _uiState.update { currentState ->
            currentState.copy(currentHouseList = housifyRepository.getHousesDetails().filter { it ->
                it.city.contains(term.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }) || it.zip.contains(term.uppercase())
            })
        }
        Log.d(TAG, "${_uiState.value.currentHouseList.count()}")
    }

}

    var houseDistance by mutableStateOf(2)
        private set
    fun calculateDistance(house: HousifyHouse, context: Context) {
        viewModelScope.launch {
            val houseLocation = Location("Place")
            houseLocation.latitude= house.latitude.toDouble()
            houseLocation.longitude = house.longitude.toDouble()
            val fusedLocationClient: FusedLocationProviderClient =
                LocationServices.getFusedLocationProviderClient(context)
            checkPermission(context)
            fusedLocationClient.lastLocation
                .addOnSuccessListener {
                    if (it == null) {
                        currentLocation = houseLocation
                    } else {
                        currentLocation = it
                    }
                }
            houseDistance =  (currentLocation.distanceTo(houseLocation) / 1000).toInt()
        }
    }
}