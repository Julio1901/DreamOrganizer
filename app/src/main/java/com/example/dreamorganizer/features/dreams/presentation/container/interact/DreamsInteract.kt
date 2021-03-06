package com.example.dreamorganizer.features.dreams.presentation.container.interact

import com.example.dreamorganizer.features.dreams.model.DreamDTO

sealed class DreamsInteract{
    data class AddNewDream (val dream : DreamDTO) : DreamsInteract()
    object DeleteDream : DreamsInteract()
    data class GetDreamFromDb (val id: Int) : DreamsInteract()
    data class ChangeSelectedDreamId(val id: Any?) : DreamsInteract()
    data class PlusOneMoreToTotalValue(val typeForCalculation: TypeForCalculation) : DreamsInteract()
    data class SubtractOneFromTotalValue(val typeForCalculation: TypeForCalculation) : DreamsInteract()
    object GetTotalUserMoney: DreamsInteract()
    object UpdateDream : DreamsInteract()
    object UpdateUserTotalMoney: DreamsInteract()
    data class PlusAmountToMoneyReserved(val value: Float): DreamsInteract()
    data class UpdateToastMessage(val stringResource: Int): DreamsInteract()
    object BuyDream: DreamsInteract()
}

enum class TypeForCalculation{
    TOTAL_VALUE, MONEY_RESERVED
}
