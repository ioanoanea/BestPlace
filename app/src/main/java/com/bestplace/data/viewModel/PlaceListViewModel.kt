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

class PlaceListViewModel: ViewModel() {

    // Place list
    private val places: MutableLiveData<MutableList<Place>> by lazy {
        MutableLiveData<MutableList<Place>>()
    }
    private val executorService: ExecutorService = Executors.newFixedThreadPool(4)
    // place repository
    private val placeRepository: PlaceRepository = PlaceRepository(executorService)

    /**
     * Return places list
     * @return LiveData<MutableList<Place>>
     */
    fun getPlaces(): LiveData<MutableList<Place>> {
        return places
    }

    /**
     * Search places by name or category
     * @param str String
     */
    fun search(str: String) {
        // clear old data
        places.value?.clear()
        if (str != "") {
            // call repository search by name
            placeRepository.searchByName(str) { nameSearchResult ->
                when (nameSearchResult) {
                    is FirebaseRepository.Result.Success<MutableList<Place>> -> {
                        // call repository search by category
                        placeRepository.searchByCategory(str) { categorySearchResult ->
                            when (categorySearchResult) {
                                is FirebaseRepository.Result.Success<MutableList<Place>> -> {
                                    // aux list
                                    val list = mutableListOf<Place>()

                                    // if name search result is not null add result elements to list
                                    if (nameSearchResult.data != null) {
                                        list.addAll(nameSearchResult.data)
                                    }

                                    // if category search result is not null add result elements to list
                                    if (categorySearchResult.data != null) {
                                        list.addAll(categorySearchResult.data)
                                    }

                                    // set new value for place list
                                    places.value = list
                                }
                                else ->
                                    throw Exception("Error: Something went wrong!")
                            }
                        }
                    }
                    else ->
                        throw Exception("Error: Something went wrong!")
                }
            }
        }
    }

    /**
     * Get all places by category
     * @param strCategory String
     */
    fun getByCategory(strCategory: String) {
        // clear old data
        places.value?.clear()
        if (strCategory != "") {
            // call repository search by category
            placeRepository.searchByCategory(strCategory) { result ->
                when (result) {
                    is FirebaseRepository.Result.Success<MutableList<Place>> -> {
                        if (result.data != null) {
                            // set new value for place list
                            places.value = result.data
                        }
                    }
                    else ->
                        throw Exception("Error: Something went wrong!")
                }
            }
        }
    }

}