package com.example.movielibrary.ui.settingInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.movielibrary.R
import com.example.movielibrary.dataBase.SqLiteDatabase

class FragmentDetailsHistory : Fragment(){
    private lateinit var container: ViewGroup
    private lateinit var layout: LinearLayout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (container != null) {
            this.container = container
        }
        layout = inflater.inflate(R.layout.layout,container,false) as LinearLayout
        return layout
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        createHistory()
    }

    private fun createHistory(){
        val sqlDB = SqLiteDatabase(requireContext())
        val array : ArrayList<String> = sqlDB.queryFilm("0","name")

        for (i in 0 until array.size) {
            val customView: View = layoutInflater.inflate(R.layout.custom_text, container, false)
            val text = customView.findViewById<TextView>(R.id.textCustom)
            text.text = array[i]
            layout.addView(customView)
        }
    }

}