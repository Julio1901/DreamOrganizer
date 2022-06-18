package com.example.dreamorganizer.presentation.container.interact

sealed class MainInteractEvent {
    object GetAllDreamFromDb : MainInteractEvent()
    object GetMoney : MainInteractEvent()
    data class ChangeTotalMoneyValue(val totalMoneyValue: Float, val changeTotalMoneyValueOptions: ChangeTotalMoneyValueOptions): MainInteractEvent()


    //TODO: Remove it
    data class AddTotalMoney (val totalMoneyValue: Float) : MainInteractEvent()
    data class RemoveTotalMoney (val totalMoneyValueToRemove: Float) : MainInteractEvent()
}
