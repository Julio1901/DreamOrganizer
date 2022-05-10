package com.example.dreamorganizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class TestFirstFragmentFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_test_first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val buttonNavigationTest : Button = view.findViewById(R.id.bt_first_fragment_to_second_container_test_navigation)
        val navigationViewModel : NavigationViewModel by sharedViewModel()



        buttonNavigationTest.setOnClickListener {
            navigationViewModel.secondNavigation(true)
        }



    }


}