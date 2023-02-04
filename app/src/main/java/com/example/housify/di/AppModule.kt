package com.example.housify.di

import android.app.Application
import com.example.housify.data.DefaultHousifyRepository
import com.example.housify.data.HousifyRepository
import com.example.housify.data.network.Constant
import com.example.housify.data.network.HousifyApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @OptIn(ExperimentalSerializationApi::class)
    @Provides
    @Singleton
    fun provideHousifyApi(): HousifyApiService {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .baseUrl(Constant.BASE_URL)
            .build()

        val retrofitService: HousifyApiService by lazy{
            retrofit.create(HousifyApiService::class.java)
        }
        return retrofitService
    }

    @Provides
    @Singleton
    fun provideHousifyRepository(housifyApiService: HousifyApiService, app: Application): HousifyRepository {
        return DefaultHousifyRepository(housifyApiService,app)
    }
}