package com.example.dreamorganizer.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.UseCase.GetAllDreamsUseCase
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.presentation.container.interact.HomeInteractEvent
import kotlinx.coroutines.launch

class HomeViewModel (private val getAllDreamsUseCase: GetAllDreamsUseCase
) : ViewModel() {

    private val _listOfDreams = MutableLiveData<List<DreamDTO>>()
    val listOfDreams : MutableLiveData<List<DreamDTO>>
        get() = _listOfDreams

    fun interpret(homeInteract: HomeInteractEvent){
        when (homeInteract){
            is HomeInteractEvent.GetAllDreamFromDb -> getAllDreamsFromDb()
        }
    }

    private fun getAllDreamsFromDb() {
        viewModelScope.launch {
          _listOfDreams.value =  getAllDreamsUseCase.execute()
        }

    }


}