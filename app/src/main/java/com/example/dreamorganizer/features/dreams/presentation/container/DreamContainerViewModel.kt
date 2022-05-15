package com.example.dreamorganizer.features.dreams.presentation.container

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamContainerNavigationEvent

class DreamContainerViewModel : ViewModel() {

    private val _navigationEvent = MutableLiveData<DreamContainerNavigationEvent>()
    val navigationEvent : MutableLiveData<DreamContainerNavigationEvent>
        get() = _navigationEvent


    fun interpretNavigation(navigationEvent : DreamContainerNavigationEvent){
       _navigationEvent.value = navigationEvent
    }


}