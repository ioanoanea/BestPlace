package com.bestplace.data.repository

import com.bestplace.data.model.Location
import java.util.concurrent.Executor

class LocationRepository(executor: Executor): FirebaseRepository("locations", executor) {

    /**
     * Get location by id
     * @param id String
     * @param callback Function1<Result<Location>, Unit>
     */
    fun getById(id: String, callback: (Result<Location>) -> Unit) {
        // execute query in background
        executor.execute {
            getCollectionReference()
                .document(id)
                .get()
                .addOnSuccessListener { result ->
                    if (result.exists()) {
                        // get location details
                        val latitude = "${result.get("latitude")}".toDouble()
                        val longitude = "${result.get("longitude")}".toDouble()

                        callback(Result.Success(Location(latitude, longitude)))
                    } else {
                        // return null if location not found
                        callback(Result.Success(null))
                    }
                }
                .addOnFailureListener {
                    callback(Result.Error(it))
                }
        }
    }
}