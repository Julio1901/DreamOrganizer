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


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



//       val navHostFragment =
//            childFragmentManager.findFragmentById(R.id.fragment_main_container) as NavHostFragment?

        val mainViewModel  by sharedViewModel<MainViewModel>()

        val btnGoToDetail : Button = view.findViewById(R.id.bt_home_fragment_go_to_detail)

        btnGoToDetail.setOnClickListener {
            //navHostFragment!!.navController.navigate(R.id.action_homeFragment2_to_dreamDetailFragment2)

            //val action = HomeFragmentDirections.actionHomeFragment2ToDreamDetailFragment2()
            //view.findNavController().navigate(action)

            mainViewModel.interpretNavigation(MainNavigationEvent.OnNavigateToDreamDetail)

        }





    }


}