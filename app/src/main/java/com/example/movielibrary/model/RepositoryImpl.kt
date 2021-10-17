package com.example.movielibrary.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movielibrary.model.rest.LoadMovie

class RepositoryImpl : Repository {
    private var listFilm: ArrayList<Film> = ArrayList(16)
    private var flag = true

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getListFilm(listCinemaID: List<Int>): ArrayList<Film> {
        listFilm.clear()
        loadMovie(listCinemaID)

        return listFilm
    }

    override fun getInfoFilm(position: Int): Film {
        val film = listFilm[position]

        Thread.sleep(200)
        return film
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovie(listCinemaID: List<Int>){
        for (i in listCinemaID.indices) {
            val film = LoadMovie.loadMovie(listCinemaID[i])

            if (film != null) {
                listFilm.add(Film(film.name, film.poster.url, film.rating.imdb, film.genres[0].name,
                                  film.year, film.description, "_ "))
            }
        }
    }

}


    