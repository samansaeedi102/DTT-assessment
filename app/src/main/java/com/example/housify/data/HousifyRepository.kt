package com.example.housify.data

import com.example.housify.network.HousifyApiService
import com.example.housify.network.HousifyHouse
import com.example.housify.ui.screens.HousifyUiState

interface HousifyRepository {
    suspend fun getHousesDetails(): List<HousifyHouse>
    //suspend fun getSelectedHouse(): HousifyHouse
}

class DefaultHousifyRepository(private val housifyApiService: HousifyApiService): HousifyRepository {
    override suspend fun getHousesDetails(): List<HousifyHouse> {
        return housifyApiService.getHouses("98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    }

    /*override suspend fun getSelectedHouse(): HousifyHouse {
        return housifyApiService.getSelectedHouse("98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    }*/

}