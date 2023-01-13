package com.example.housify.data

import com.example.housify.network.HousifyApiService
import com.example.housify.network.HousifyHouse

interface HousifyRepository {
    suspend fun getHousesDetails(): List<HousifyHouse>
}

class DefaultHousifyRepository(private val housifyApiService: HousifyApiService): HousifyRepository {
    override suspend fun getHousesDetails(): List<HousifyHouse> {
        return housifyApiService.getHouses("98bww4ezuzfePCYFxJEWyszbUXc7dxRx")
    }
}