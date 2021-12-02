package com.example.movielibrary.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movielibrary.R
import com.example.movielibrary.model.Film
import com.example.movielibrary.ui.detailsFragment.DetailsFragmentFilm
import com.example.movielibrary.ui.fragments.FragmentOne
import com.example.movielibrary.ui.settingInfo.FragmentSetting
import com.example.movielibrary.ui.fragments.FragmentTwo
import com.example.movielibrary.ui.settingInfo.FragmentFavourites
import com.example.movielibrary.ui.settingInfo.FragmentInfo
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var toolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar = findViewById(R.id.toolbar)

        setFirstFragment()
        navigationSelected()
        toolbarMenu()
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

    private fun toolbarMenu(){
        toolbar.setOnMenuItemClickListener {
            if(it.itemId == R.id.optionsToolbarSetting){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, FragmentSetting())
                    .addToBackStack("Stack1")
                    .commit()
            }
            if(it.itemId == R.id.optionsToolbarInfo){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, FragmentInfo())
                    .addToBackStack("Stack1")
                    .commit()
            }
            if(it.itemId == R.id.optionsToolbarFavourites){
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.containerMain, FragmentFavourites())
                    .addToBackStack("Stack1")
                    .commit()
            }
            return@setOnMenuItemClickListener false
        }
    }

}