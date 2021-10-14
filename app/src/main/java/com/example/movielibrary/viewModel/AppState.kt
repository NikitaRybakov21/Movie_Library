package com.example.movielibrary.viewModel

import com.example.movielibrary.model.Film
import com.example.movielibrary.model.InfoFilm

sealed class AppState {
    data class SuccessRecyclerFilm(val listFilm: ArrayList<Film>) : AppState()
    data class SuccessInfoDetails(val infoFilm: InfoFilm) : AppState()

    object Loading : AppState()
    object LoadingInfo : AppState()

    data class Error(val error: Throwable) : AppState()
}