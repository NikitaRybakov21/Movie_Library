package com.example.movielibrary.model

interface Repository {
    fun getListFilm(listCinemaID: List<Int>): ArrayList<Film>
    fun getInfoFilm(position: Int): Film
}