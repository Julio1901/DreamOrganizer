package com.example.dreamorganizer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.adapter.DreamAdapter
import com.example.dreamorganizer.presentation.container.interact.HomeInteractEvent
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.HomeViewModel
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    lateinit var reciclerViewDreamList : RecyclerView
    lateinit var btnAddNewDream : Button
    private val navigationViewModel  by sharedViewModel<NavigationViewModel>()
    private val homeViewModel by sharedViewModel<HomeViewModel>()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClicks()
        setUpListeners()
        populateDreamList()

    }

    private fun populateDreamList() {
        homeViewModel.interpret(HomeInteractEvent.GetAllDreamFromDb)
    }

    private fun initViews(view: View){
        view.let {
            reciclerViewDreamList = it.findViewById(R.id.rv_home_dream_list)
            btnAddNewDream = it.findViewById(R.id.bt_home_fragment_add_new_dream)
        }
    }

    private fun setupClicks(){
        btnAddNewDream.setOnClickListener {
           navigationViewModel.interpretNavigation(HomeNavigationEvent.OnNavigateToRegisterNewDreamGraph)
        }
    }

    private fun setUpListeners(){
        homeViewModel.listOfDreams.observe(viewLifecycleOwner, Observer {
            reciclerViewDreamList.adapter = DreamAdapter(it, navigationViewModel)
        } )
    }

}