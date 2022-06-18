package com.example.dreamorganizer.di

import com.example.dreamorganizer.UseCase.*
import com.example.dreamorganizer.features.dreams.UseCase.*
import com.example.dreamorganizer.features.dreams.presentation.container.DreamContainerViewModel
import com.example.dreamorganizer.features.dreams.repository.DreamsRepository
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.example.dreamorganizer.features.dreams.viewModel.DreamFeaturesViewModel
import com.example.dreamorganizer.presentation.viewModel.HomeViewModel
import com.example.dreamorganizer.repository.TotalMoneyRepository
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    viewModel {NavigationViewModel()}
    viewModel { DreamFeaturesViewModel(get(), get(), get()) }
    viewModel { DreamContainerViewModel() }
    viewModel { HomeViewModel(get(), get(), get(), get())}
}

val useCase = module {
    factory <GetDreamUseCase> {GetDreamUseCaseImpl(get())}
    factory <SetDreamUseCase> {SetDreamUseCaseImpl(get())}
    factory <GetAllDreamsUseCase> {GetAllDreamsUseCaseImpl(get())}
    factory <GetMoneyUseCase> {GetMoneyUseCaseImpl(get()) }
    factory <InsertTotalMoneyUseCase> {InsertTotalMoneyUseCaseImpl(get())}
    factory <UpdateTotalMoneyUseCase> {UpdateTotalMoneyUseCaseImpl(get())}
    factory <DeleteDreamUseCase> {DeleteDreamUseCaseImpl(get())}
}

val repository = module {
    factory<DreamsRepository>{DreamsRepository(androidContext())}
    factory<TotalMoneyRepository>{ TotalMoneyRepository(androidContext()) }
}



