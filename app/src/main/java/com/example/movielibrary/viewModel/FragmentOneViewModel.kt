package com.example.movielibrary.viewModel

import androidx.lifecycle.*
import com.example.movielibrary.model.Repository

class FragmentOneViewModel(private val repository: Repository) : ViewModel() , LifecycleObserver  {
    private val liveData = MutableLiveData<AppState>()

    fun getLiveData(): LiveData<AppState> = liveData

    fun getListFilm(list: List<Int>) {
        liveData.value = AppState.Loading

        Thread {
            liveData.postValue(AppState.SuccessRecyclerFilm(repository.getListFilm(list)))
        }.start()
    }

    fun getInfoFilm(positions: Int){
        liveData.value = AppState.LoadingInfo

        Thread {
            liveData.postValue(AppState.SuccessInfoDetails(repository.getInfoFilm(positions)))
        }.start()
    }

}