package com.example.movielibrary.model

interface Repository {
    fun getListFilm(listCinemaID: List<Int>): ArrayList<Film>
    fun getListFilmTopIMDB(listCinemaID: List<Int>): ArrayList<Film>
    fun getInfoFilm(position: Int): Film
    fun getInfoFilmTop(position: Int): Film
}