package com.bestplace.data.model

data class Place (
    override val latitude: Double? = null,
    override val longitude: Double? = null,
    val name: String,
    val description: String,
    val category: String,
    val address: String,
    val picture: String,
): Location(latitude, longitude)