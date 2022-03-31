package com.example.dreamorganizer.presentation

import android.R.attr.width
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.adapter.DreamAdapter
import com.example.dreamorganizer.model.DreamVO
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.MainViewModel
import com.example.dreamorganizer.util.ImageManager
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
        val reciclerViewDreamList : RecyclerView = view.findViewById(R.id.rv_home_dream_list)
        val buttonTest : Button = view.findViewById(R.id.bt_home_fragment_test_navigation)


        buttonTest.setOnClickListener {
            mainViewModel.interpretNavigation(MainNavigationEvent.OnNavigateToSecondGraph)
        }

        //val imageManager = ImageManager()
        //val mockImageBitMap = imageManager.saveImageInBank(eiffelTowerImage.drawable.toBitmap())
    }

}