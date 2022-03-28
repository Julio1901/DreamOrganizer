package com.example.dreamorganizer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val _navigationEvent = MutableLiveData<MainNavigationEvent>()
    val navigationEvent : MutableLiveData<MainNavigationEvent>
        get() = _navigationEvent



    fun interpretNavigation (interact : MainNavigationEvent){
        _navigationEvent.value = interact
    }

}