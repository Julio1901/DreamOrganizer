package com.example.dreamorganizer.presentation.container.interact

sealed class MainInteractEvent {
    object GetAllDreamFromDb : MainInteractEvent()
    object GetMoney : MainInteractEvent()
    data class ChangeTotalMoneyValue(val totalMoneyValue: Float, val changeTotalMoneyValueOptions: ChangeTotalMoneyValueOptions): MainInteractEvent()
    data class ChangeAlertDialogMessage(val stringResourceMessage: Int) : MainInteractEvent()
    data class ChangeVisibilityIconTotalMoney (val btnId: Int, val isVisible: Boolean) : MainInteractEvent()
    data class ChangeVisibilityIconRestOfTheMoney (val btnId: Int, val isVisible: Boolean) : MainInteractEvent()
    //TODO: Remove it

    data class RemoveTotalMoney (val totalMoneyValueToRemove: Float) : MainInteractEvent()
}
