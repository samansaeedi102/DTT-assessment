package com.example.housify.data.network

import retrofit2.http.GET
import retrofit2.http.Header

interface HousifyApiService {
    @GET("house")
    suspend fun getHouses(@Header("Access-Key")Access_Key: String): List<HousifyHouse>
}

