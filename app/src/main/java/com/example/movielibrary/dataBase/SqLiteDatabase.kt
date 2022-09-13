package com.example.movielibrary.dataBase

import android.annotation.SuppressLint
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.core.content.contentValuesOf

class SqLiteDatabase(context: Context) {
    private val dataBase : SQLiteDatabase = AppSqliteHelper(context).writableDatabase

    @SuppressLint("Range", "Recycle")
    fun queryFilm(id : String,attribute : String ) : ArrayList<String> {

        val array: ArrayList<String> = ArrayList(16)
        val cursor = dataBase.query(
            "Film", arrayOf(attribute),
            null,null,
            null,null,null
        )

        if(cursor.moveToFirst()) {
            do {
                array.add(cursor.getString(cursor.getColumnIndex(attribute)))
            } while (cursor.moveToNext())
        }

        return array
    }

    fun insertFilm(name : String, timeView : String, id_kp : Int){
        dataBase.insert("Film", null,
            contentValuesOf(
            "name" to name,
            "timeView" to timeView,
            "id_kp" to id_kp)
        )
    }

    fun deleteTable(){
        dataBase.delete("Film", null, null);
    }

}