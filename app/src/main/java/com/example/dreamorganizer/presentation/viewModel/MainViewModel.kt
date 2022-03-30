package com.example.dreamorganizer.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent

class MainViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<MainNavigationEvent>()
    val navigationEvent : MutableLiveData<MainNavigationEvent>
        get() = _navigationEvent



    fun interpretNavigation (interact : MainNavigationEvent){
        _navigationEvent.value = interact
    }

}