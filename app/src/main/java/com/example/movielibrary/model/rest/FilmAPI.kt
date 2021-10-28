package com.example.movielibrary.model.rest

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FilmAPI {
    @GET("movie")
    fun getFilm(@Query("search") id: Int,
                @Query("field") i: String,
                @Query("token") key: String) : Call<MovieDTO>
}
