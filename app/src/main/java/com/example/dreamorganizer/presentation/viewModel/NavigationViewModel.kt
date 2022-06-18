package com.example.dreamorganizer.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent

class NavigationViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<HomeNavigationEvent>()
    val navigationEvent : MutableLiveData<HomeNavigationEvent>
        get() = _navigationEvent

    fun interpretNavigation (interact : HomeNavigationEvent){
        _navigationEvent.value = interact
    }

}