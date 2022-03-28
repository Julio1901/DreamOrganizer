package com.example.dreamorganizer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController


class HomeFragment : Fragment() {



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnGoToDetail : Button = view.findViewById(R.id.bt_home_fragment_go_to_detail)

        btnGoToDetail.setOnClickListener {
            val action = HomeFragmentDirections.actionHomeFragmentToDreamDetailFragment()
            view.findNavController().navigate(action)
        }

    }


}