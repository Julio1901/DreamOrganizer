package com.example.dreamorganizer.presentation.container.interact

sealed class MainInteractEvent {
    object GetAllDreamFromDb : MainInteractEvent()
    object GetMoney : MainInteractEvent()
    data class ChangeTotalMoneyValue(val totalMoneyValue: Float, val changeTotalMoneyValueOptions: ChangeTotalMoneyValueOptions): MainInteractEvent()
    data class ChangeAlertDialogMessage(val stringResourceMessage: Int) : MainInteractEvent()

    //TODO: Remove it

    data class RemoveTotalMoney (val totalMoneyValueToRemove: Float) : MainInteractEvent()
}
