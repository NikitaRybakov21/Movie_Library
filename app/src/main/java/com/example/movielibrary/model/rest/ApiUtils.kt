package com.example.movielibrary.model.rest

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object ApiUtils {
    const val baseUrl = "https://api.kinopoisk.dev/"

    fun getOkHTTPBuilderWithHeaders(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()

        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(10, TimeUnit.SECONDS)
        httpClient.writeTimeout(10, TimeUnit.SECONDS)

        httpClient.addInterceptor { chain ->

            val original = chain.request()
            val request = original.newBuilder()
             //   .header("X-Yandex-API-Key", "7a436743-4c9e-415e-9edc-cc6b53f7c987")
                .method(original.method(), original.body())
                .build()

            chain.proceed(request)
        }

        return httpClient.build()
    }
}