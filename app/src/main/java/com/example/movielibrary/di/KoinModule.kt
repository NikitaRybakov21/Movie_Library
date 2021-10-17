package com.example.movielibrary.di

import com.example.movielibrary.model.Repository
import com.example.movielibrary.model.RepositoryImpl
import com.example.movielibrary.viewModel.FragmentOneViewModel
import com.example.movielibrary.viewModel.FragmentTwoViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single<Repository> { RepositoryImpl() }
    //View models
    viewModel { FragmentOneViewModel(get()) }
    viewModel { FragmentTwoViewModel(get()) }
}