package com.example.movielibrary.viewModel

import androidx.lifecycle.*
import com.example.movielibrary.model.Repository
import java.lang.Thread.sleep

class FragmentOneViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getListFilm() = getListFilmLocal()

    private fun getListFilmLocal() {
        liveData.value = AppState.Loading
        Thread {
            sleep(0)
            liveData.postValue(AppState.Success(repository.getListFilmLocal()))
        }.start()
    }
}