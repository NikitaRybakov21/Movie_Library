package com.example.movielibrary.model

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.movielibrary.model.rest.FilmRepo

class RepositoryImpl : Repository {
   // private val API_KEY = "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06"
    private val API_KEY = "274f828ad283bd634ef4fc1ee4af255f"

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
        val films = FilmRepo.api.getFilm(API_KEY).execute().body()

        films?.result?.forEach { film ->
            listFilm.add(Film(film.name, "https://api.themoviedb.org" + film.poster, film.rating, "Фантастика",
                1987, film.description, " ",555))
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun loadMovieTop(listCinemaID: List<Int>) {
        val films = FilmRepo.api.getFilm(API_KEY).execute().body()

        films?.result?.forEach { film ->
            listFilm.add(
                Film(film.name, "https://api.themoviedb.org" + film.poster, film.rating, "Фантастика",
                    1987, film.description, " ", 555))
        }
    }
}


