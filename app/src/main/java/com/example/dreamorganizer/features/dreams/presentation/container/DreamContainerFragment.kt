package com.example.dreamorganizer.features.dreams.presentation.container

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamContainerNavigationEvent
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class DreamContainerFragment : Fragment() {

   private val dreamContainerViewModel : DreamContainerViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dream_container, container, false)




    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dreamContainerViewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            handleNavigation(it)
        })

    }





    private fun handleNavigation(navigationEvent : DreamContainerNavigationEvent){
        when(navigationEvent){
            is DreamContainerNavigationEvent.NavigateToHome -> navigateToHome()
        }
    }

    private fun navigateToHome(){
        //TODO navigate to first graph here
       //findNavController().navigate(R.id.action_to_home)
    }

}