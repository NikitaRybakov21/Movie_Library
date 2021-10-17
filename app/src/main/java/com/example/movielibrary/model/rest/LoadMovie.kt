package com.example.movielibrary.model.rest

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.gson.Gson
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.MalformedURLException
import java.net.URL
import java.util.stream.Collectors
import javax.net.ssl.HttpsURLConnection

object LoadMovie {
    private const val API_KEY = "ZQQ8GMN-TN54SGK-NB3MKEC-ZKB8V06"

    @RequiresApi(Build.VERSION_CODES.N)
    fun loadMovie(id: Int): MovieDTO? {
        try {
            val uri = URL("https://api.kinopoisk.dev/movie?search=$id&field=id&token=$API_KEY")

            lateinit var urlConnection: HttpsURLConnection
            try {
                urlConnection = uri.openConnection() as HttpsURLConnection
                urlConnection.requestMethod = "GET"
                urlConnection.readTimeout = 10000

                val bufferedReader = BufferedReader(InputStreamReader(urlConnection.inputStream))
                return Gson().fromJson(getLines(bufferedReader), MovieDTO::class.java)

            } catch (e: Exception) {
                Log.e("", "Fail connection", e)
                e.printStackTrace()
            } finally {
                urlConnection.disconnect()
            }

        } catch (e: MalformedURLException) {
            Log.e("", "Fail URI", e)
            e.printStackTrace()
        }
        return null
    }

    @RequiresApi(Build.VERSION_CODES.N)
    private fun getLines(reader: BufferedReader): String {
        return reader.lines().collect(Collectors.joining("\n"))
    }

}