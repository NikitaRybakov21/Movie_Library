package com.example.movielibrary.model.rest

data class MovieDTO(
    val name: String,
    val year: Int,
    val description: String,

    val poster: FilmDTO,
    val rating: FilmDTO,
    val genres: ArrayList<FilmDTO>,
    val budget: FilmDTO
)

data class FilmDTO(
    val url: String,
    val imdb: Float,
    val name: String,
    var value: String = "_"
)


