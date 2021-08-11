package com.bestplace

import com.bestplace.data.model.Location
import com.bestplace.data.model.Place
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.repository.LocationRepository
import com.bestplace.data.repository.PlaceRepository
import org.junit.Assert.*
import org.junit.Test
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class TestRepository {

    @Test
    fun testPlaceRepository() {
        val executorService: ExecutorService = Executors.newFixedThreadPool(4)
        val placeRepository = PlaceRepository(executorService)

        // ty to search by existing name - full word
        placeRepository.searchByName("test") { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> ->
                    assertNotNull(result.data)
                else ->
                    assertTrue(result is Exception)
            }
        }

        // try to search by existing name - incomplete word
        placeRepository.searchByName("tes") { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> ->
                    assertNotNull(result.data)
                else ->
                    assertTrue(result is Exception)
            }
        }

        // try search by non existing name
        placeRepository.searchByName("non existing") { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> ->
                    assertNull(result.data)
                else ->
                    assertTrue(result is Exception)
            }
        }

        // ty to search by existing category - full word
        placeRepository.searchByCategory("food") { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> ->
                    assertNotNull(result.data)
                else ->
                    assertTrue(result is Exception)
            }
        }

        // try to search by existing name - incomplete word
        placeRepository.searchByCategory("car") { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> ->
                    assertNotNull(result.data)
                else ->
                    assertTrue(result is Exception)
            }
        }

        // try search by non existing name
        placeRepository.searchByCategory("non existing") { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> ->
                    assertNull(result.data)
                else ->
                    assertTrue(result is Exception)
            }
        }
    }
}