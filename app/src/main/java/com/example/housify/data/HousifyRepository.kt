package com.example.housify.data

import android.app.Application
import com.example.housify.data.network.Constant
import com.example.housify.data.network.HousifyApiService
import com.example.housify.data.network.HousifyHouse
/**
 * Provides repository.
 */
interface HousifyRepository {
    suspend fun getHousesDetails(): List<HousifyHouse>
}

/**
 * Implements functions provided in repository
 */
class DefaultHousifyRepository(
    private val housifyApiService: HousifyApiService,
    app: Application,
) : HousifyRepository {
    override suspend fun getHousesDetails(): List<HousifyHouse> {
        return housifyApiService.getHouses(Constant.KEY).sortedBy { it.price }
    }
}
