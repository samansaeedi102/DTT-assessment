package com.example.housify.data.network

import kotlinx.serialization.Serializable
/**
 * Represents JSON holder fetched by API.
 */
@Serializable
data class HousifyHouse(
    val id: Int,
    val image: String,
    val price: Int,
    val bedrooms: Int,
    val bathrooms: Int,
    val size: Int,
    val description: String,
    var zip: String,
    val city: String,
    val latitude: Int,
    val longitude: Int,
    val createdDate: String,
    //used for calculating distance between each house and current user
    var distance: Int = 0
)
