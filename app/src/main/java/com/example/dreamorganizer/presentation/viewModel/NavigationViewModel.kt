package com.example.dreamorganizer.presentation.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent

class NavigationViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<HomeNavigationEvent>()
    val navigationEvent : MutableLiveData<HomeNavigationEvent>
        get() = _navigationEvent

    private val _secondContainerNavigation = MutableLiveData<Boolean>()
    val secondContainerNavigation
        get() = _secondContainerNavigation

    fun interpretNavigation (interact : HomeNavigationEvent){
        _navigationEvent.value = interact
    }

    fun secondNavigation(buttonHasClicked : Boolean){
        _secondContainerNavigation.value = true
    }

}