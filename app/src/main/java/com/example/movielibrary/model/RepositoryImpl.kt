package com.example.movielibrary.model

import com.example.movielibrary.R

class RepositoryImpl : Repository {
    var listFilm : ArrayList<Film> = ArrayList(16)

    override fun getListFilmLocal() = listFilm

    override fun createListFilm() {
        listFilm.clear()

        listFilm.add(Film("Blade Runner 2049", R.drawable.film3, 8.0f,"Фантастика",2017))
        listFilm.add(Film("Casablanca", R.drawable.film1 , 8.5f,"Мелодрамма",1942))
        listFilm.add(Film("Pulp fiction", R.drawable.film2 , 8.9f,"Криминал",1994))
        listFilm.add(Film("Tenet", R.drawable.film4 , 7.4f,"Фантастика",2020))
    }

    override fun getInfoFilm(position: Int): InfoFilm {
        val film = listFilm[position]

        Thread.sleep(700)

        return InfoFilm(film.filmName,film.imagePoster)
    }

}
    