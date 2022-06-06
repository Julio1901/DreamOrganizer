package com.example.dreamorganizer.presentation.container.interact

sealed class HomeNavigationEvent {
    data class OnNavigateToDreamDetail (val dreamId: Int) : HomeNavigationEvent()
    object OnNavigateToRegisterNewDreamGraph : HomeNavigationEvent()
}