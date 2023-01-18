package com.example.housify.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.housify.HousifyApplication
import com.example.housify.data.HousifyRepository
import com.example.housify.network.HousifyHouse
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface HousifyUiState {
    data class Success(val houses: List<HousifyHouse>) : HousifyUiState
    object Error: HousifyUiState
    object Loading: HousifyUiState
}

class HousifyViewModel(private val housifyRepository: HousifyRepository): ViewModel() {
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
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as HousifyApplication)
                val housifyRepository = application.container.housifyRepository
                HousifyViewModel(housifyRepository = housifyRepository)
            }
        }
    }
}