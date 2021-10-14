package com.example.movielibrary.model

interface Repository {
    fun getListFilmLocal(): ArrayList<Film>
    fun getInfoFilm(position: Int): InfoFilm
    fun createListFilm()
}