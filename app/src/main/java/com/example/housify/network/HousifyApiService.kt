package com.example.housify.network

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Header
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType


interface HousifyApiService {
    @GET("house")
    suspend fun getHouses(@Header("Access-Key")Access_Key: String): List<HousifyHouse>

//    @GET("house")
//    suspend fun getSelectedHouse(@Header("Access-Key")Access_Key: String): HousifyHouse
}

