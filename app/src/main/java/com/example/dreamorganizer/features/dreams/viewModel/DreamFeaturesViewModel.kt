package com.example.dreamorganizer.features.dreams.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
import kotlinx.coroutines.launch

class DreamFeaturesViewModel (private val getDreamUseCase: GetDreamUseCase,
                              private val setDreamUseCase: SetDreamUseCase) : ViewModel(){


    private val _selectedDreamId = MutableLiveData<Int>()
    val selectedDreamId : MutableLiveData<Int>
        get () = _selectedDreamId


    fun interpret (interact: DreamsInteract){
        when (interact){
            is DreamsInteract.AddNewDream -> addNewDream(interact.dream)
            is DreamsInteract.GetDreamFromDb -> getDreamFromDb(interact.id)
            is DreamsInteract.ChangeSelectedDreamId -> changeSelectedDreamId(interact.id)
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
             //TODO make method here
        }
    }

    private fun changeSelectedDreamId(id: Int){
        _selectedDreamId.value = id
    }

}