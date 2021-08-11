package com.bestplace.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bestplace.data.model.Location

class LocationViewModel: ViewModel() {

    private val currentLocation: MutableLiveData<Location> by lazy {
        MutableLiveData<Location>()
    }

    /**
     * Set current location
     * @param location Location
     */
    fun setLocation(location: Location) {
        this.currentLocation.value = location
    }

    /**
     * Get current location live data
     */
    fun getCurrentLocation(): LiveData<Location> {
        return currentLocation
    }
}