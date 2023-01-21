package com.example.housify.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.housify.data.HousifyRepository
import com.example.housify.network.HousifyHouse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject


sealed interface HousifyUiState {
    data class Success(val houses: List<HousifyHouse>) : HousifyUiState
    object Error: HousifyUiState
    object Loading: HousifyUiState
}

@HiltViewModel
class HousifyViewModel @Inject constructor(private val housifyRepository: HousifyRepository): ViewModel() {
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
            housifyUiState = try {
                HousifyUiState.Success(housifyRepository.getHousesDetails().sortedBy { it.price })
            } catch (e: IOException) {
                HousifyUiState.Error
            }
        }
    }
}