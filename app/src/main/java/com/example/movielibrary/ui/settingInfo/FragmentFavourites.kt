package com.example.movielibrary.ui.settingInfo

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movielibrary.R
import com.example.movielibrary.dataBase.AppSqliteHelper
import com.example.movielibrary.dataBase.SqLiteDatabase

class FragmentFavourites : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_favourites,container,false)
    }

    @SuppressLint("Recycle", "Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textFavourites = view.findViewById<TextView>(R.id.favourites1)
        val textFavourites2 = view.findViewById<TextView>(R.id.favourites2)

        val sqlDB = SqLiteDatabase(requireContext())

        sqlDB.insertFilm("Дюна","заметка о дюне")
        sqlDB.insertFilm("Матрица","заметка о Матрице")

        textFavourites.text = sqlDB.getQueryFilm("1","name")
        textFavourites2.text = sqlDB.getQueryFilm("2","name")
    }

}