package com.example.dreamorganizer.features.dreams.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.dreamorganizer.MainActivity
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.presentation.activity.EditDreamFlowActivity
import com.example.dreamorganizer.util.navigateToNavGraph


class EditDreamFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_dream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val btnBackToHome : Button = view.findViewById(R.id.bt_edit_dream_back_to_home)

        //TODO move it to container and apply viewModelDirections pattern
        btnBackToHome.setOnClickListener {
            navigateToNavGraph(MainActivity::class.java)
        }

    }

}