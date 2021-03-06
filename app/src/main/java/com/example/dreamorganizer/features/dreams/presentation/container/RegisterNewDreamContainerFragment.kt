package com.example.dreamorganizer.features.dreams.presentation.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.dreamorganizer.MainActivity
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamContainerNavigationEvent
import com.example.dreamorganizer.util.navigateToNavGraph
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class RegisterNewDreamContainerFragment : Fragment() {

   private val navigationDreamViewModel : DreamContainerViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_new_dream_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigationDreamViewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            handleNavigation(it)
        })

    }

    private fun handleNavigation(navigationEvent : DreamContainerNavigationEvent){
        when(navigationEvent){
            is DreamContainerNavigationEvent.NavigateToHome -> navigateToHome()

        }
    }

    private fun navigateToHome(){
       navigateToNavGraph(MainActivity::class.java)
    }












}