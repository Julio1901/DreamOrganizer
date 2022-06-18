package com.example.dreamorganizer.presentation.container.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.NavHostFragment
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.presentation.activity.AddNewDreamFlowActivity
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.example.dreamorganizer.util.navigateToNavGraph
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import androidx.lifecycle.Observer
import com.example.dreamorganizer.features.dreams.presentation.activity.EditDreamFlowActivity

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


        setupObserver()

    }

    private fun setupObserver() {
        navigationViewModel.navigationEvent.observe(viewLifecycleOwner, Observer {
            handleNavigation(it)
        })
    }

    private fun handleNavigation(it : HomeNavigationEvent){
        when(it){
            is HomeNavigationEvent.OnNavigateToDreamDetail -> goToEditDream(it.dreamId)
            is HomeNavigationEvent.OnNavigateToRegisterNewDreamGraph -> goToRegisterNewDreamGraph()
        }

    }

    private fun goToRegisterNewDreamGraph(){
        navigateToNavGraph(AddNewDreamFlowActivity::class.java)
    }

    private fun goToEditDream(dreamId: Int) {
        navigateToNavGraph(EditDreamFlowActivity::class.java, dreamId = dreamId)
    }


}