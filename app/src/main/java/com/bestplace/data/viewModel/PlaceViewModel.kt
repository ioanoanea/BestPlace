package com.bestplace.data.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bestplace.data.model.Place
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.repository.PlaceRepository
import java.lang.Exception
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlaceViewModel: ViewModel() {

    private val place: MutableLiveData<Place> by lazy {
        MutableLiveData<Place>()
    }
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    // place repository
    private val placeRepository: PlaceRepository = PlaceRepository(executorService)

    /**
     * Get current stored place live data
     * @return LiveData<Place>
     */
    fun getPlace(): LiveData<Place> {
        return place
    }

    /**
     * Fetch place by id from repository
     * @param id String
     */
    fun fetchPlace(id: String) {
        // call place repository get by id
        placeRepository.getById(id) { result ->
            when (result) {
                is FirebaseRepository.Result.Success<Place> ->
                    // update place value
                    place.value = result.data
                else ->
                    // throw exception
                    throw Exception("Error: Something went wrong!")
            }
        }
    }
}