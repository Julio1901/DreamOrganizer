package com.example.dreamorganizer.presentation

import android.R.attr.width
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.dreamorganizer.R
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.MainViewModel
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

        val mainViewModel  by sharedViewModel<MainViewModel>()
        val btTest : Button = view.findViewById(R.id.bt_ui_test)
        val tvTest : TextView = view.findViewById(R.id.tv_iu_test)

        btTest.setOnClickListener {
            tvTest.text = "Was clicked - Ui Test"

        }


    }


}