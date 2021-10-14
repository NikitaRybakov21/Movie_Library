package com.example.movielibrary.ui.main

import android.widget.Toast
import com.example.movielibrary.ui.fragments.FragmentOne

fun FragmentOne.setToast(title: String){
    Toast.makeText(requireContext(),title, Toast.LENGTH_SHORT).show()
}