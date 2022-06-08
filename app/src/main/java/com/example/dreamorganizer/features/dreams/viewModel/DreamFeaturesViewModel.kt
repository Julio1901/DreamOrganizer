package com.example.dreamorganizer.features.dreams.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
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



}