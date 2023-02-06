package com.example.housify.ui.screens

import androidx.compose.runtime.*
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housify.data.HousifyRepository
import com.example.housify.data.network.HousifyHouse
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.SphericalUtil
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

/**
 * Provides state for updating UI
 */
data class UiState(
    var searchUnsuccessful: Boolean = false,
    val currentSearchedTerm: String = "",
    val noInternet: Boolean = false
)

@HiltViewModel
class HousifyViewModel @Inject constructor(private val housifyRepository: HousifyRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow(UiState())
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    private val _housesList = mutableStateListOf<HousifyHouse>()
    var housesList: List<HousifyHouse> = _housesList

    var selectedHouse by mutableStateOf<HousifyHouse?>(null)
        private set

    private var _userCurrentLng = mutableStateOf(0.0)
    var userCurrentLng: MutableState<Double> = _userCurrentLng

    private var _userCurrentLat = mutableStateOf(0.0)
    var userCurrentLat: MutableState<Double> = _userCurrentLat

    var userLatLng: LatLng = LatLng(userCurrentLat.value, userCurrentLng.value)

    private var _locationPermissionGranted = MutableLiveData(false)
    var locationPermissionGranted: LiveData<Boolean> = _locationPermissionGranted

    fun currentUserLatLng(latLng: LatLng) {
        _userCurrentLat.value = latLng.latitude
        _userCurrentLng.value = latLng.longitude
    }

    fun grantLocationPermission(setGranted: Boolean) {
        _locationPermissionGranted.value = setGranted
    }

    fun selectedHouseChanged(newHouse: HousifyHouse) {
        selectedHouse = newHouse
    }

    /**
     * Delete the list of houses on resume in order to avoid duplication.
     */
    fun deleteCurrentHousesOnResume() {
        _housesList.clear()
    }

    /**
     * Delete the list of houses on pause in order to avoid duplication.
     */
    fun deleteCurrentHousesOnPause() {
        _housesList.clear()
    }

    /**
     * Load all the houses from API and if the permission is granted, calculated distance of user
     * to each house and omit the space in ZIP property to follow design and maker search consistent.
     * Because in design zip code has no space between digits and letters, but the zip code loaded from
     * the API has a space by default.
     */
    fun getHouses() {
        viewModelScope.launch {
            try {
                _housesList.addAll(housifyRepository.getHousesDetails())
                if (locationPermissionGranted.value == true) {
                    _housesList.forEach {
                        val houseLatLng = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
                        it.distance = (SphericalUtil.computeDistanceBetween(
                            userLatLng,
                            houseLatLng
                        ) / 1000).toInt()
                        it.zip = it.zip.replace("\\s".toRegex(), "")
                    }
                }
                _uiState.update {
                    it.copy(noInternet = false)
                }
            } catch (e: IOException) {
                _uiState.update {
                    it.copy(noInternet = true)
                }
            }
        }
    }

    /**
     * Filter the list of houses based on the input received from user.
     */
    fun searchHouse(term: String) {
        viewModelScope.launch(Dispatchers.Default) {
            housesList = _housesList.filter { it ->
                it.city.contains(term.replaceFirstChar {
                    if (it.isLowerCase()) it.titlecase(
                        Locale.getDefault()
                    ) else it.toString()
                }) || it.zip.contains(term.uppercase())
            }
            updateSearchState()
            updateSearchedTerm(term)
        }
    }

    /**
     * If search is not successful, empty search will be shown.
     */
    private fun updateSearchState() {
        if (housesList.isEmpty()) {
            _uiState.update {
                it.copy(searchUnsuccessful = true)
            }
        } else {
            _uiState.update {
                it.copy(searchUnsuccessful = false)
            }
        }
    }

    /**
     * Closes empty search and goes back to home page.
     */
    fun closeEmptySearchScreen() {
        viewModelScope.launch(Dispatchers.Default) {
            _uiState.update {
                it.copy(
                    searchUnsuccessful = false,
                )
            }
            housesList = _housesList
        }
    }


    fun updateSearchedTerm(term: String) {
        _uiState.update {
            it.copy(currentSearchedTerm = term)
        }
    }

    fun deleteSearchedTerm() {
        _uiState.update {
            it.copy(currentSearchedTerm = "")
        }
    }
}