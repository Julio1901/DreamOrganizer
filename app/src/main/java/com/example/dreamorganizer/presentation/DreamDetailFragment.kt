package com.example.dreamorganizer.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.MainViewModel
import com.example.dreamorganizer.R
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DreamDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_dream_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mainViewModel  by sharedViewModel<MainViewModel>()

        val btnNavigateToHome : Button = view.findViewById(R.id.bt_dream_detail_fragment_go_to_home)

        btnNavigateToHome.setOnClickListener {
            mainViewModel.interpretNavigation(MainNavigationEvent.OnNavigateToHome)
        }


    }


}