package com.example.movielibrary.dataBase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AppSqliteHelper(private val appContext: Context) : SQLiteOpenHelper(appContext,"dataBaseFilm3",null,1) {

    override fun onCreate(p0: SQLiteDatabase?) {

        val sql = appContext.assets.open("BD.sql").bufferedReader().use {
            it.readText()
        }
        sql.split(';')
           .filter { it.isNotBlank() }
           .forEach {
                p0?.execSQL(it)
            }
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }

}