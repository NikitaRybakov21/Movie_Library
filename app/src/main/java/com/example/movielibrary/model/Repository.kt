package com.example.movielibrary.model

interface Repository {
    fun getWeatherFromServer(): Film
    fun getListFilmLocal(): ArrayList<Film>
}