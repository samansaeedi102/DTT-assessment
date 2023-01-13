package com.example.housify.ui.screens

import android.media.Image
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housify.data.DefaultHousifyRepository
import com.example.housify.network.HousifyApi
import kotlinx.coroutines.launch
import java.io.IOException

sealed interface HousifyUiState {
    data class Success(val houses: String) : HousifyUiState
    object Error: HousifyUiState
    object Loading: HousifyUiState
}

class HousifyViewModel: ViewModel() {
    var housifyUiState: HousifyUiState by mutableStateOf(HousifyUiState.Loading)
        private set

    init {
        getHouses()
    }

    private fun getHouses() {
        viewModelScope.launch {
            housifyUiState = try {
                val housesRepository = DefaultHousifyRepository()
                val listResult = housesRepository.getHousesDetails()
                HousifyUiState.Success("you got ${listResult.size}")
            } catch (e: IOException) {
                HousifyUiState.Error
            }
        }
    }
}