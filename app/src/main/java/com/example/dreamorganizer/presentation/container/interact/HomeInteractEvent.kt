package com.example.dreamorganizer.presentation.container.interact

sealed class HomeInteractEvent {
    object GetAllDreamFromDb : HomeInteractEvent()
    object GetMoney : HomeInteractEvent()
    data class AddTotalMoney (val totalMoneyValue: Float) : HomeInteractEvent()
    data class RemoveTotalMoney (val totalMoneyValueToRemove: Float) : HomeInteractEvent()
}
