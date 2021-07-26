package com.bestplace.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.concurrent.Executor

open class FirebaseRepository(
    private val collectionName: String,
    val executor: Executor
) {

    /**
     * Query result class
     * @param out R
     */
    sealed class Result<out R> {
        data class Success<out T>(val data: T? = null): Result<T>()
        data class Error(val exception: Exception): Result<Nothing>()
    }

    /**
     * Get Firestore collection reference
     * @return CollectionReference
     */
    fun getCollectionReference(): CollectionReference {
        return Firebase.firestore.collection(collectionName)
    }

}