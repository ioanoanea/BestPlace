package com.bestplace.data.model

import kotlin.math.round

open class Location (
    open val id: String? = null,
    open val latitude: Double? = null,
    open val longitude: Double? = null,
) {
    /**
     * Get distance from this location to a given location
     * @param location Location
     * @return Float
     */
    fun getDistance(location: Location): Float {
        val locationA = android.location.Location("location A")
        val locationB = android.location.Location("location B")

        // set location A coordinates to this location coordinates
        locationA.latitude = this.latitude!!
        locationA.longitude = this.longitude!!

        // set location B coordinates to received location coordinates
        locationB.latitude = location.latitude!!
        locationB.longitude = location.longitude!!

        return round(locationA.distanceTo(locationB) / 100) / 10

    }
}