package com.bestplace.data.viewModel

import androidx.lifecycle.ViewModel
import com.bestplace.data.model.Place
import com.bestplace.data.repository.FirebaseRepository
import com.bestplace.data.repository.PlaceRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class PlaceListViewModel: ViewModel() {
    // Place list
    var places: MutableList<Place> = mutableListOf()
        private set(value) {field = value}
    private val placeRepository: PlaceRepository
    private var onUpdateListener: OnUpdateListener? = null

    init {
        val executorService: ExecutorService = Executors.newFixedThreadPool(4)
        this.placeRepository = PlaceRepository(executorService)
    }

    /**
     * Set on update listener
     * @param onUpdateListener OnUpdateListener
     */
    fun setOnUpdateListener(onUpdateListener: OnUpdateListener) {
        this.onUpdateListener = onUpdateListener
    }

    fun searchByName(strName: String) {
        placeRepository.searchByName(strName) { result ->
            when (result) {
                is FirebaseRepository.Result.Success<MutableList<Place>> -> {
                    if (result.data != null) {
                        this.places = result.data
                        onUpdateListener?.onUpdate()
                    } else {
                        onUpdateListener?.onNullResult()
                    }
                } else ->
                    onUpdateListener?.onError(result)
            }
        }
    }

}