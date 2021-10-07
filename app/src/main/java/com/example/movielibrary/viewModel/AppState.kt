package com.example.movielibrary.viewModel

import com.example.movielibrary.model.Film

sealed class AppState {
    data class Success(val listFilm: ArrayList<Film>) : AppState()
    data class Error(val error: Throwable) : AppState()
    object Loading : AppState()
}