package com.bestplace.data.model

data class Place (
    override val latitude: Long,
    override val longitude: Long,
    val name: String,
    val description: String,
    val type : String,
): Location(latitude, longitude)