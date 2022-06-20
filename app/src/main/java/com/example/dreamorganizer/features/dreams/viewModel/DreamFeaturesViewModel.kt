package com.example.dreamorganizer.features.dreams.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.R
import com.example.dreamorganizer.UseCase.GetMoneyUseCase
import com.example.dreamorganizer.UseCase.UpdateTotalMoneyUseCase
import com.example.dreamorganizer.features.dreams.UseCase.DeleteDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.UpdateDreamUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
import com.example.dreamorganizer.features.dreams.presentation.container.interact.TypeForCalculation
import com.example.dreamorganizer.model.TotalMoneyDTO
import kotlinx.coroutines.launch
import java.lang.Exception

class DreamFeaturesViewModel (private val getDreamUseCase: GetDreamUseCase,
                              private val setDreamUseCase: SetDreamUseCase,
                              private val deleteDreamUseCase: DeleteDreamUseCase,
                              private val updateDreamUseCase: UpdateDreamUseCase,
                              private val getMoneyUseCase: GetMoneyUseCase,
                              private val updateTotalMoneyUseCase: UpdateTotalMoneyUseCase) : ViewModel(){


    private val _selectedDream = MutableLiveData<DreamDTO>()
    val selectedDream : MutableLiveData<DreamDTO>
        get () = _selectedDream

    private var _dreamHasEdited = false
    val dreamHasEdited: Boolean
        get() = _dreamHasEdited

    private val _toastMessage = MutableLiveData<Int>()
    val toastMessage : MutableLiveData<Int>
        get() = _toastMessage


    lateinit var totalMoney : TotalMoneyDTO

    fun interpret (interact: DreamsInteract){
        when (interact){
            is DreamsInteract.AddNewDream -> addNewDream(interact.dream)
            is DreamsInteract.DeleteDream -> deleteDream()
            is DreamsInteract.GetDreamFromDb -> getDreamFromDb(interact.id)
            is DreamsInteract.ChangeSelectedDreamId -> changeSelectedDreamId(interact.id)
            is DreamsInteract.PlusOneMoreToTotalValue -> plusOneMoreToTotalValue(interact.typeForCalculation)
            is DreamsInteract.SubtractOneFromTotalValue -> subtractOneFromTotalValue(interact.typeForCalculation)
            is DreamsInteract.UpdateDream -> updateDream()
            is DreamsInteract.GetTotalUserMoney -> getTotalMoney()
            is DreamsInteract.UpdateUserTotalMoney -> updateTotalMoney()
            is DreamsInteract.PlusAmountToMoneyReserved -> plusAmountToMoneyReserved(interact.value)
            is DreamsInteract.UpdateToastMessage -> updateToastMessage(interact.stringResource)
        }
    }

    private fun updateTotalMoney() {
        viewModelScope.launch {
            updateTotalMoneyUseCase.execute(totalMoney)
        }
    }

    private fun updateDream() {
       viewModelScope.launch {
           _selectedDream.value.let {
               if(it != null){
                   updateDreamUseCase.execute(it)
               }
           }
       }
    }


    private fun addNewDream(dreamDTO : DreamDTO){
        viewModelScope.launch {
            setDreamUseCase.execute(dreamDTO)
        }
    }

    private fun deleteDream(){
         viewModelScope.launch {
             _selectedDream.value.let {
                 if (it != null){
                     deleteDreamUseCase.execute(it)
                     totalMoney.restOfTheMoney += it.totalMoneyReserved
                     updateTotalMoney()
                 }
             }
         }
    }

    private fun getDreamFromDb(id: Int){
        viewModelScope.launch {
            val response = getDreamUseCase.execute(id)
            _selectedDream.value = response
        }
    }

    private fun changeSelectedDreamId(id: Any?){
        if (id != null){
            try {
                getDreamFromDb(id as Int)
            }catch ( e: Exception){
                //TODO: Log error here
            }
        }
    }

    private fun updateToastMessage(stringResource: Int){
        _toastMessage.value = stringResource
    }

    private fun plusAmountToMoneyReserved(value: Float){
        _selectedDream.value?.totalMoneyReserved.let {
            if(it != null && value <= totalMoney.restOfTheMoney){
                _selectedDream.value?.totalMoneyReserved = it + value
                totalMoney.restOfTheMoney = totalMoney.restOfTheMoney - value
                updateDreamLiveDate()
            }else{
                _toastMessage.value = R.string.you_have_no_more_money_to_add
            }
        }
    }

    private fun plusOneMoreToTotalValue(typeForCalculation: TypeForCalculation){
        if (typeForCalculation == TypeForCalculation.MONEY_RESERVED){
            _selectedDream.value?.totalMoneyReserved.let {
                if (it != null && totalMoney.restOfTheMoney >= 1) {
                        _selectedDream.value?.totalMoneyReserved = it + 1f
                        totalMoney.restOfTheMoney = totalMoney.restOfTheMoney - 1f
                    }else{
                        _toastMessage.value = R.string.you_have_no_more_money_to_add
                    }

                updateDreamLiveDate()
            }

        }else{
           _selectedDream.value?.value.let {
                if(it != null){
                    _selectedDream.value?.value = it + 1f
                }
           }
            updateDreamLiveDate()
        }
    }

    private fun subtractOneFromTotalValue(typeForCalculation: TypeForCalculation){
        if (typeForCalculation == TypeForCalculation.MONEY_RESERVED){
            _selectedDream.value?.totalMoneyReserved.let {
                if (it != null && it > 0) {
                    _selectedDream.value?.totalMoneyReserved = it - 1f
                    totalMoney.restOfTheMoney = totalMoney.restOfTheMoney + 1f
                }
                updateDreamLiveDate()
            }

        }else{
            _selectedDream.value?.value.let {
                if(it != null && it > 0){
                    _selectedDream.value?.value = it - 1f
                }
            }
            updateDreamLiveDate()
        }
    }



    private fun updateDreamLiveDate(){
        _selectedDream.value = _selectedDream.value
        _dreamHasEdited = true
    }

    private fun getTotalMoney() {
        viewModelScope.launch {
            val response = getMoneyUseCase.execute()
            totalMoney = response[0]

        }
    }

}