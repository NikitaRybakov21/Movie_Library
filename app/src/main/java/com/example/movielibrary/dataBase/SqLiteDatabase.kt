package com.example.movielibrary.dataBase

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

class SqLiteDatabase(context: Context) {
    private val dataBase : SQLiteDatabase = AppSqliteHelper(context).writableDatabase

    @SuppressLint("Range", "Recycle")
    fun getQueryFilm(id : String,attribute : String ) : String {
        val cursor = dataBase.query(
            "Film",          arrayOf(attribute),
            "${"id"} = ?", arrayOf(id),
            null,null,null
        )
        cursor.moveToFirst()
        val data = cursor.getString(cursor.getColumnIndex(attribute))

        cursor.close()
        return data
    }

    fun insertFilm(name : String, notes : String){
        dataBase.insert("Film",
                 null, contentValuesOf("name" to name, "notes" to notes)
        )
    }

}