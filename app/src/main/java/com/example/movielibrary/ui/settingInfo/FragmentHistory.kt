package com.example.movielibrary.ui.settingInfo

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movielibrary.R
import com.example.movielibrary.dataBase.AppSqliteHelper
import com.example.movielibrary.dataBase.SqLiteDatabase
import com.example.movielibrary.ui.main.MainActivity

class FragmentHistory : Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
       return inflater.inflate(R.layout.fragment_history,container,false) as LinearLayout
    }

    @SuppressLint("Recycle", "Range")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createDetailsHistory()

        val sqlDB = SqLiteDatabase(requireContext())

        val button = view.findViewById<Button>(R.id.buttonDeleteHistory)
        button.setOnClickListener {
            sqlDB.deleteTable()
            createDetailsHistory()
        }
    }

    private fun createDetailsHistory(){
        childFragmentManager
            .beginTransaction()
            .replace(R.id.containerDetailsHistory,FragmentDetailsHistory())
            .commit()
    }

}


