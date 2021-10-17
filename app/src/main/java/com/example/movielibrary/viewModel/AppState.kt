package com.example.movielibrary.viewModel

import com.example.movielibrary.model.Film

sealed class AppState {
    data class SuccessRecyclerFilm(val listFilm: ArrayList<Film>) : AppState()
    data class SuccessInfoDetails(val infoFilm: Film) : AppState()

    object Loading : AppState()
    object LoadingInfo : AppState()

    data class Error(val error: Throwable) : AppState()
}