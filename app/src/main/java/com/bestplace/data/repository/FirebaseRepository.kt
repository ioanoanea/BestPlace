package com.bestplace.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

abstract class FirebaseRepository<T>(
    private val collectionName: String
) {
    /**
     * Get Firestore collection reference
     * @return CollectionReference
     */
    fun getCollectionReference(): CollectionReference {
        return Firebase.firestore.collection(collectionName)
    }

}