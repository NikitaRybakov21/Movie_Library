package com.example.movielibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movielibrary.R
import com.example.movielibrary.model.Film
import com.example.movielibrary.ui.detailsFragment.DetailsFragmentFilm
import com.example.movielibrary.ui.fragments.FragmentOne
import com.example.movielibrary.ui.fragments.FragmentTwo
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

    fun addFragmentDetails(infoFilm: Film){
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
                    val fragmentOne = FragmentOne.newInstance()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerMain, fragmentOne)
                        .commit()

                    fragmentOne.setMainActivity(this)
                    true
                }
                R.id.options2 -> {
                    val fragmentTwo = FragmentTwo.newInstance()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.containerMain, fragmentTwo)
                        .commit()

                    fragmentTwo.setMainActivity(this)
                    true
                }
                R.id.options3 -> {

                    true
                }
                else -> false
            }
        }
    }

}