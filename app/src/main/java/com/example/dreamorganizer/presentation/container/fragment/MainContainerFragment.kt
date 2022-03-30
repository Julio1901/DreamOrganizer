package com.example.dreamorganizer.presentation.container.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.MainViewModel
import com.example.dreamorganizer.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class MainContainerFragment : Fragment() {

    lateinit var navHostFragment: NavHostFragment
    private val mainViewModel : MainViewModel by sharedViewModel()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_main_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupObserver()

    }

    private fun goToHome() {
        navHostFragment.navController.navigate(R.id.action_dreamDetailFragment2_to_homeFragment2)
    }

    private fun goToDream() {
        navHostFragment.navController.navigate(R.id.action_homeFragment2_to_dreamDetailFragment2)
    }

    private fun setupObserver() {
        mainViewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            handleNavigation(it)
        })

    }

    private fun initViews(){
        view?.let {
            navHostFragment =
            childFragmentManager.findFragmentById(R.id.fragment_home_container) as NavHostFragment
        }
    }


    private fun handleNavigation(it : MainNavigationEvent){
        when(it){
            is MainNavigationEvent.OnNavigateToDreamDetail -> goToDream()
            is MainNavigationEvent.OnNavigateToHome -> goToHome()
        }

    }

}