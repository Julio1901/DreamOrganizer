package com.example.dreamorganizer.features.dreams.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
import com.example.dreamorganizer.features.dreams.presentation.container.interact.TypeForCalculation
import kotlinx.coroutines.launch
import java.lang.Exception

class DreamFeaturesViewModel (private val getDreamUseCase: GetDreamUseCase,
                              private val setDreamUseCase: SetDreamUseCase) : ViewModel(){


    private val _selectedDream = MutableLiveData<DreamDTO>()
    val selectedDream : MutableLiveData<DreamDTO>
        get () = _selectedDream


    fun interpret (interact: DreamsInteract){
        when (interact){
            is DreamsInteract.AddNewDream -> addNewDream(interact.dream)
            is DreamsInteract.GetDreamFromDb -> getDreamFromDb(interact.id)
            is DreamsInteract.ChangeSelectedDreamId -> changeSelectedDreamId(interact.id)
            is DreamsInteract.PlusOneMoreToTotalValue -> plusOneMoreToTotalValue(interact.typeForCalculation)
            is DreamsInteract.SubtractOneFromTotalValue -> subtractOneFromTotalValue(interact.typeForCalculation)
        }
    }



    private fun addNewDream(dreamDTO : DreamDTO){
        viewModelScope.launch {
            setDreamUseCase.execute(dreamDTO)
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

    private fun plusOneMoreToTotalValue(typeForCalculation: TypeForCalculation){
        if (typeForCalculation == TypeForCalculation.MONEY_RESERVED){
            _selectedDream.value?.totalMoneyReserved.let {
                if (it != null) {
                    _selectedDream.value?.totalMoneyReserved = it + 1f
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
    }

}