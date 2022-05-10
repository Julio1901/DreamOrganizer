package com.example.dreamorganizer.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dreamorganizer.features.dreams.UseCase.GetDreamUseCase
import com.example.dreamorganizer.features.dreams.UseCase.SetDreamUseCase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import kotlinx.coroutines.launch

class MainViewModel (private val getDreamUseCase: GetDreamUseCase,
                     private val setDreamUseCase: SetDreamUseCase) : ViewModel(){


    fun testSet(dreamDTO : DreamDTO){
        viewModelScope.launch {
            setDreamUseCase.execute(dreamDTO)
        }
    }

    fun testGet(id: Int){
        viewModelScope.launch {
            val response = getDreamUseCase.execute(id)
                Log.e("Test", response.name!!)
        }
    }

}