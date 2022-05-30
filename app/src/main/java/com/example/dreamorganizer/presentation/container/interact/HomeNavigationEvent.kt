package com.example.dreamorganizer.presentation.container.interact

sealed class HomeNavigationEvent {
    object OnNavigateToDreamDetail : HomeNavigationEvent()
    object OnNavigateToRegisterNewDreamGraph : HomeNavigationEvent()
}