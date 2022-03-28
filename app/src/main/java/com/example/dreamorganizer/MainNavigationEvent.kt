package com.example.dreamorganizer

sealed class MainNavigationEvent {
    object OnNavigateToDreamDetail : MainNavigationEvent()
}