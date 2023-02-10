package com.example.housify.data.network

import com.example.housify.data.network.Constant.ACCESS_KEY
import retrofit2.http.GET
import retrofit2.http.Header
/**
 * Runs GET query
 */
interface HousifyApiService {
    @GET("house")
    suspend fun getHouses(@Header(ACCESS_KEY) Access_Key: String): List<HousifyHouse>
}

