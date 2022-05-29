package com.example.dreamorganizer.presentation.container.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.presentation.activity.AddNewDreamFlowActivity
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.example.dreamorganizer.util.navigateToNavGraph
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.lifecycle.Observer

class HomeContainerFragment : Fragment() {


    private lateinit var navHostFragment: NavHostFragment
    private val navigationViewModel : NavigationViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_container, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setupObserver()

    }

    private fun goToRegisterNewDreamGraph(){
        navigateToNavGraph(AddNewDreamFlowActivity::class.java)
    }


    private fun setupObserver() {
        navigationViewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            handleNavigation(it)
        })

    }

    private fun initViews(){
        view?.let {
            //TODO build method
        }
    }


    private fun handleNavigation(it : MainNavigationEvent){
        when(it){
            //is MainNavigationEvent.OnNavigateToDreamDetail -> goToEditDream()
            is MainNavigationEvent.OnNavigateToRegisterNewDreamGraph -> goToRegisterNewDreamGraph()

        }

    }



}