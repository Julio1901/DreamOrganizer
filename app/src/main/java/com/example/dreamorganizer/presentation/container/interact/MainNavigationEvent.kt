package com.example.dreamorganizer.presentation.container.interact

sealed class MainNavigationEvent {
    object OnNavigateToDreamDetail : MainNavigationEvent()
    object OnNavigateToHome : MainNavigationEvent()
    object OnNavigateToRegisterNewDreamGraph : MainNavigationEvent()
}