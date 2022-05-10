package com.example.dreamorganizer.di

import android.content.Context
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCaseImpl
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCaseImpl
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.example.dreamorganizer.viewModel.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {NavigationViewModel()}
    viewModel {MainViewModel(get(), get()) }
}

val useCase = module {
    factory <GetDreamUseCase>{ GetDreamUseCaseImpl(get())}
    factory<SetDreamUseCase> { SetDreamUseCaseImpl(get())}
}

val repository = module {
    factory<DreamsRepository>{DreamsRepository()}
}