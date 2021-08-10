package com.bestplace.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bestplace.data.model.Location
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.repository.LocationRepository
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocationViewModel: ViewModel() {

    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    private val locationRepository: LocationRepository = LocationRepository(executorService)
    private val location: MutableLiveData<Location> by lazy {
        MutableLiveData<Location>()
    }

    /**
     * Returns location live data
     * @return MutableLiveData<Location>
     */
    fun getLocation(): LiveData<Location> {
        return location
    }

    /**
     * Get location by id
     * @param id String
     */
    fun getById(id: String) {
        // call location repository get by id
        locationRepository.getById(id) { result ->
            when (result) {
                is FirebaseRepository.Result.Success<Location> ->
                    // if result is not null update location
                    if (result.data != null) {
                        this.location.value = result.data
                    }
                else ->
                    throw Exception("Error: Something went wrong!")
            }
        }
    }

}