package com.example.dreamorganizer.di

import com.example.dreamorganizer.presentation.viewModel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {

    viewModel {
        MainViewModel()
    }


}