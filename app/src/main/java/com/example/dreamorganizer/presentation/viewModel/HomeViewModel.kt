package com.example.dreamorganizer.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.UseCase.GetAllDreamsUseCase
import com.example.dreamorganizer.UseCase.GetMoneyUseCase
import com.example.dreamorganizer.UseCase.InsertTotalMoneyUseCase
import com.example.dreamorganizer.UseCase.UpdateTotalMoneyUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.model.TotalMoneyDTO
import com.example.dreamorganizer.presentation.container.interact.HomeInteractEvent
import kotlinx.coroutines.launch

class HomeViewModel (private val getAllDreamsUseCase: GetAllDreamsUseCase,
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


    fun interpret(homeInteract: HomeInteractEvent){
        when (homeInteract){
            is HomeInteractEvent.GetAllDreamFromDb -> getAllDreamsFromDb()
            is HomeInteractEvent.GetMoney -> getTotalMoney()
            is HomeInteractEvent.AddTotalMoney -> updateTotalMoney(homeInteract.totalMoneyValue)


        }
    }

    private fun updateTotalMoney(amountToAddToTheTotalMoney: Float) {
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
                }
            }
          
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



}