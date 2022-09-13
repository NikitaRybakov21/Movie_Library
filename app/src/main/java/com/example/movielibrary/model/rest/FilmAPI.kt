package com.example.movielibrary.model.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {
    @GET("discover/movie")
    fun getFilm(
        @Query("api_key") key: String
    ) : Call<ArrayMovieDTO>
}
