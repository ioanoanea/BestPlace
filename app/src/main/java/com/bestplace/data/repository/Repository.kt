package com.bestplace.data.repository

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

abstract class Repository<T> {

    // Firestore collection name
    protected abstract val collection: String
    // items update listener
    protected var onUpdateListener: (()->Unit)? = null
    // data items
    val items: MutableList<T> = mutableListOf()

    // Get collection from Firestore
    protected fun getCollection(): CollectionReference {
        return Firebase.firestore.collection(collection)
    }

    // get all items from collection
    // to be implemented
    abstract fun getAll()

    @JvmName("setOnUpdateListener1")
    fun setOnUpdateListener(onUpdateListener: (()->Unit)) {
        this.onUpdateListener = onUpdateListener
    }
}