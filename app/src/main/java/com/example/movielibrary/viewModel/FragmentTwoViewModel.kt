package com.example.movielibrary.viewModel

import androidx.lifecycle.*
import com.example.movielibrary.model.Repository

class FragmentTwoViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getListFilm(listCinemaID: List<Int>) {
        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(AppState.SuccessRecyclerFilm(repository.getListFilmTopIMDB(listCinemaID)))
        }.start()
    }

    fun getInfoFilm(positions: Int){
        liveData.value = AppState.LoadingInfo

        Thread {
            liveData.postValue(AppState.SuccessInfoDetails(repository.getInfoFilmTop(positions)))
        }.start()
    }

}
