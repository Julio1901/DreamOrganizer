package com.example.dreamorganizer.presentation.container.interact

sealed class HomeInteractEvent {
    object GetAllDreamFromDb : HomeInteractEvent()
}
