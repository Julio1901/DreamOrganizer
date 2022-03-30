package com.example.dreamorganizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ContainerDreamDetailFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_container_dream_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel : MainViewModel by sharedViewModel()

        mainViewModel.navigationEvent.observe(this, Observer {

            when(it){
                MainNavigationEvent.OnNavigateToHome -> showDreamDetail()
            }
        })



    }

    private fun showDreamDetail() {
        //var navHostFragment: NavHostFragment
        //navHostFragment =
          //  childFragmentManager.findFragmentById(R.id.fragment_dream_detail_container) as NavHostFragment
        //navHostFragment.navController.navigate(R.id.action_dreamDetailFragment2_to_homeFragment2)

        view!!.findNavController().navigate(R.id.action_dreamDetailFragment2_to_homeFragment2)

    }

}