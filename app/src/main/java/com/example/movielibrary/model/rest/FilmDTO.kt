package com.example.movielibrary.model.rest

import com.google.gson.annotations.SerializedName

data class ArrayMovieDTO(@field:SerializedName("results") val result: ArrayList<MovieDTO>)

data class MovieDTO(
    @field:SerializedName("original_title") val name: String,
    @field:SerializedName("release_date") val year: String,
    @field:SerializedName("overview") val description: String,

    @field:SerializedName("poster_path") val poster: String,
    @field:SerializedName("vote_average") val rating: Float,
    @field:SerializedName("genres") val genres: ArrayList<FilmDTO>,
    @field:SerializedName("budget") val budget: Int
)

data class FilmDTO(
    @field:SerializedName("name") val name: String,
)


