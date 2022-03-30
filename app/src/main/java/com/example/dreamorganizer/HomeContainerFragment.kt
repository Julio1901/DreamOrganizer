package com.example.dreamorganizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class HomeContainerFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel : MainViewModel by sharedViewModel()

        mainViewModel.navigationEvent.observe(this, Observer {
            when(it){
                MainNavigationEvent.OnNavigateToDreamDetail -> goToDream()
                MainNavigationEvent.OnNavigateToHome -> goToHome()
            }
        })





    }

    private fun goToHome() {
        var navHostFragment: NavHostFragment

        navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_home_container) as NavHostFragment
        navHostFragment.navController.navigate(R.id.action_dreamDetailFragment2_to_homeFragment2)
    }

    private fun goToDream() {
        var navHostFragment: NavHostFragment

        navHostFragment =
           childFragmentManager.findFragmentById(R.id.fragment_home_container) as NavHostFragment
        navHostFragment.navController.navigate(R.id.action_homeFragment2_to_dreamDetailFragment2)
        
    }

}