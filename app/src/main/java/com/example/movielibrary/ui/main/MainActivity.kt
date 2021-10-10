package com.example.movielibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movielibrary.R
import com.example.movielibrary.model.InfoFilm
import com.example.movielibrary.ui.detailsFragment.DetailsFragmentFilm
import com.example.movielibrary.ui.fragments.FragmentOne
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFirstFragment()
        navigationSelected()
    }

    private fun setFirstFragment(){
        val fragmentOne = FragmentOne.newInstance()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerMain, fragmentOne)
            .commit()

        fragmentOne.setMainActivity(this)
    }

    fun addFragmentDetails(infoFilm: InfoFilm){
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.containerMain, DetailsFragmentFilm.newInstance(infoFilm))
            .addToBackStack("Stack1")
            .commit()
    }

    private fun navigationSelected(){
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.options1 -> {
                    // Respond to navigation item 1 click
                    true
                }
                R.id.options2 -> {
                    // Respond to navigation item 2 click
                    true
                }
                R.id.options3 -> {
                    // Respond to navigation item 2 click
                    true
                }
                else -> false
            }
        }
    }

}