package com.bestplace.data.viewModel

import com.bestplace.data.model.Place
import com.bestplace.data.repository.FirebaseRepository

interface OnUpdateListener {
    fun onUpdate()
    fun onNullResult()
    fun onError(e: FirebaseRepository.Result<MutableList<Place>>)
}