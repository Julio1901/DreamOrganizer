package com.example.dreamorganizer.presentation.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.R
import com.example.dreamorganizer.UseCase.GetAllDreamsUseCase
import com.example.dreamorganizer.UseCase.GetMoneyUseCase
import com.example.dreamorganizer.UseCase.InsertTotalMoneyUseCase
import com.example.dreamorganizer.UseCase.UpdateTotalMoneyUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.model.TotalMoneyDTO
import com.example.dreamorganizer.presentation.container.interact.ChangeTotalMoneyValueOptions
import com.example.dreamorganizer.presentation.container.interact.MainInteractEvent
import kotlinx.coroutines.launch

class MainViewModel (private val getAllDreamsUseCase: GetAllDreamsUseCase,
                     private val getMoneyUseCase: GetMoneyUseCase,
                     private val insertTotalMoneyUseCase: InsertTotalMoneyUseCase,
                     private val updateTotalMoneyUseCase: UpdateTotalMoneyUseCase
) : ViewModel() {

    private val _listOfDreams = MutableLiveData<List<DreamDTO>>()
    val listOfDreams : MutableLiveData<List<DreamDTO>>
        get() = _listOfDreams

    private val _totalMoney = MutableLiveData<TotalMoneyDTO>()
    val totalMoney : MutableLiveData<TotalMoneyDTO>
        get() = _totalMoney

    private val _alertDialogMessage = MutableLiveData<Int>()
    val alertDialogMessage: MutableLiveData<Int>
        get() = _alertDialogMessage


    fun interpret(mainInteract: MainInteractEvent){
        when (mainInteract){
            is MainInteractEvent.GetAllDreamFromDb -> getAllDreamsFromDb()
            is MainInteractEvent.GetMoney -> getTotalMoney()
            is MainInteractEvent.ChangeTotalMoneyValue -> changeTotalMoneyValue(mainInteract.totalMoneyValue, mainInteract.changeTotalMoneyValueOptions)
            is MainInteractEvent.ChangeAlertDialogMessage -> changeAlertDialogMessage(mainInteract.stringResourceMessage)
        }
    }

    private fun changeTotalMoneyValue(value: Float, changeTotalMoneyValueOptions: ChangeTotalMoneyValueOptions) {

        if(changeTotalMoneyValueOptions == ChangeTotalMoneyValueOptions.ADD_VALUE){
            plusTotalMoney(value)
        }else if(changeTotalMoneyValueOptions == ChangeTotalMoneyValueOptions.REMOVE_VALUE){
            decreaseTotalValue(value)
        }
    }


    private fun plusTotalMoney(amountToAddToTheTotalMoney: Float) {
        viewModelScope.launch {
            
            _totalMoney.value?.totalMoney.let { 
                if (it != null){
                    _totalMoney.value?.totalMoney = it + amountToAddToTheTotalMoney
                }
            }
            
           _totalMoney.value?.restOfTheMoney.let {
               if(it!= null){
                   _totalMoney.value?.restOfTheMoney = it + amountToAddToTheTotalMoney
               }
           }

            _totalMoney.value.let {
                if(it != null){
                    updateTotalMoneyUseCase.execute(it)
                    _totalMoney.value = it
                    changeAlertDialogMessage(R.string.monetary_value_update)
                }
            }
          
        }
    }

    private fun decreaseTotalValue(value: Float) {
        viewModelScope.launch {
            if (checkIfHasMoneyToDecrease(value)){
                _totalMoney.value?.totalMoney.let {
                    if (it != null && it > value){
                        _totalMoney.value?.totalMoney = it - value
                    }
                }

                _totalMoney.value?.restOfTheMoney.let {
                    if(it!= null && it > value){
                        _totalMoney.value?.restOfTheMoney = it - value
                    }
                }

                _totalMoney.value.let {
                    if(it != null){
                        updateTotalMoneyUseCase.execute(it)
                        _totalMoney.value = it
                        changeAlertDialogMessage(R.string.monetary_value_update)
                    }
                }
            }else{
                changeAlertDialogMessage(R.string.you_dont_have_the_money_value_to_remove)
            }
        }
    }

    private fun checkIfHasMoneyToDecrease(value: Float) : Boolean{
        _totalMoney.value?.restOfTheMoney.let {
            return it!= null && it > value
        }
    }


    private fun getAllDreamsFromDb() {
        viewModelScope.launch {
            _listOfDreams.value =  getAllDreamsUseCase.execute()
        }

    }

    private fun getTotalMoney(){
        viewModelScope.launch {
            val response = getMoneyUseCase.execute()

            if(response.isEmpty()){
                val totalMoney = TotalMoneyDTO(id = 0,
                    totalMoney = 0F,
                    restOfTheMoney = 0F)

                insertTotalMoneyUseCase.execute(totalMoney)
                _totalMoney.value = totalMoney
            }else{
                _totalMoney.value = response[0]
            }
        }
    }


    private fun changeAlertDialogMessage(stringResourceMessage: Int){
        _alertDialogMessage.value = stringResourceMessage
    }


}