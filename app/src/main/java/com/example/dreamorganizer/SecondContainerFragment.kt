package com.example.dreamorganizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class SecondContainerFragment : Fragment() {

    lateinit var navHostFragment: NavHostFragment
    private val navigationViewModel : NavigationViewModel by sharedViewModel()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second_container, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        initViews()


    }




        private fun initViews(){
        view?.let {
            navHostFragment =
                childFragmentManager.findFragmentById(R.id.fragment_second_container) as NavHostFragment
        }
    }


}