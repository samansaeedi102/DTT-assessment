package com.example.housify.data.network

import kotlinx.serialization.Serializable
/**
 * Represents JSON holder fetched by API.
 * @property latitude is the latitude of the house on google maps.
 * @property longitude is the longitude of the house on google maps.
 * @property distance is used for calculating distance between each house and current user.
 * @property fullAddress is used for enhancing search operations.
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
    var distance: Int = 0,
    var fullAddress: String = ""
)
