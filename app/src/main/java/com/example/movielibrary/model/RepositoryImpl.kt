package com.example.movielibrary.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movielibrary.model.rest.FilmRepo
import com.example.movielibrary.model.rest.LoadMovie

class RepositoryImpl : Repository {
    private val API_KEY = "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06"

    private var listFilm: ArrayList<Film> = ArrayList(16)
    private var listFilmTop: ArrayList<Film> = ArrayList(16)
    private var flag = true
    private var flag2 = true

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getListFilm(listCinemaID: List<Int>): ArrayList<Film> {
        if(flag) {
            listFilm.clear()
            loadMovie(listCinemaID)
            flag = false
        }
        return listFilm
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun getListFilmTopIMDB(listCinemaID: List<Int>): ArrayList<Film> {
        if(flag2) {
            listFilmTop.clear()
            loadMovieTop(listCinemaID)
            flag2 = false
        }
        return listFilmTop
    }

    override fun getInfoFilm(position: Int): Film {
        val film = listFilm[position]

        Thread.sleep(500)
        return film
    }

    override fun getInfoFilmTop(position: Int): Film {
        val film = listFilmTop[position]

        Thread.sleep(500)
        return film
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovie(listCinemaID: List<Int>){
        for (i in listCinemaID.indices) {
          //val film = LoadMovie.loadMovie(listCinemaID[i])
            val film = FilmRepo.api.getFilm(listCinemaID[i],"id",API_KEY).execute().body()

            if (film != null) {
                listFilm.add(Film(film.name, film.poster.url, film.rating.imdb, film.genres[0].name,
                                  film.year, film.description, "_ "))
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovieTop(listCinemaID: List<Int>){
        for (i in listCinemaID.indices) {
          //val film = LoadMovie.loadMovie(listCinemaID[i])
            val film = FilmRepo.api.getFilm(listCinemaID[i],"id",API_KEY).execute().body()

            if (film != null) {
                listFilmTop.add(Film(film.name, film.poster.url, film.rating.imdb, film.genres[0].name,
                    film.year, film.description, "_ "))
            }
        }
    }

}


    