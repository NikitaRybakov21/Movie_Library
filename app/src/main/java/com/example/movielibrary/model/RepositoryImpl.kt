package com.example.movielibrary.model

import com.example.movielibrary.R

class RepositoryImpl : Repository {
    var listFilm : ArrayList<Film> = ArrayList(16)

    override fun getListFilmLocal() : ArrayList<Film> {

        listFilm.add(Film("Blade Runner 2049", R.drawable.film3, 8.0f))
        listFilm.add(Film("Casablanca", R.drawable.film1 , 8.5f))
        listFilm.add(Film("Pulp fiction", R.drawable.film2 , 8.9f))
        listFilm.add(Film("Tenet", R.drawable.film4 , 7.4f))

        return listFilm
    }

    override fun getWeatherFromServer() : Film {return Film("null",0,-1.0f)}
}

