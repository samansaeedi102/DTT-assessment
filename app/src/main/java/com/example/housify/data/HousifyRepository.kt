package com.example.housify.data

import android.app.Application
import com.example.housify.data.network.Constant
import com.example.housify.data.network.HousifyApiService
import com.example.housify.data.network.HousifyHouse
/**
 * Provides repository.
 */
interface HousifyRepository {
    /**
     * @return a lif of houses provided by API
     */
    suspend fun getHousesDetails(): List<HousifyHouse>
}

/**
 * Implements functions provided in repository
 */
class DefaultHousifyRepository(
    private val housifyApiService: HousifyApiService,
    app: Application,
) : HousifyRepository {
    /**
     * Implements getHousesDetails provided by HousesRepository interface.
     * @return the list of houses sorted based on price(cheap to expensive) descending.
     */
    override suspend fun getHousesDetails(): List<HousifyHouse> {
        return housifyApiService.getHouses(Constant.KEY).sortedBy { it.price }
    }
}
