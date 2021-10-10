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
            liveData.postValue(AppState.SuccessRecyclerFilm(repository.getListFilmLocal()))
        }.start()
    }

    fun getInfoFilm(positions: Int){
        liveData.value = AppState.LoadingInfo

        Thread {
            liveData.postValue(AppState.SuccessInfoDetails(repository.getInfoFilm(positions)))
        }.start()
    }

    fun createListFilm(){
        repository.createListFilm()
    }
}