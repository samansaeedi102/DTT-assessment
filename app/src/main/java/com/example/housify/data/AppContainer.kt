package com.example.housify.data

import com.example.housify.network.HousifyApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import retrofit2.Retrofit

interface AppContainer {
    val housifyRepository: HousifyRepository
}

class DefaultAppContainer: AppContainer {
    override val housifyRepository: HousifyRepository by lazy {
        DefaultHousifyRepository(retrofitService)
    }
    private val BASE_URL = "https://intern.d-tt.nl/api/"

    private val retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
        .baseUrl(BASE_URL)
        .build()

        val retrofitService: HousifyApiService by lazy{
            retrofit.create(HousifyApiService::class.java)
        }
}

