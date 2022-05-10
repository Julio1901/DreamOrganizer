package com.example.dreamorganizer.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.graphics.drawable.toBitmap
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.adapter.DreamAdapter
import com.example.dreamorganizer.model.DreamVO
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
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
        //TODO Refactor this to implement initViews function
        val mainViewModel  by sharedViewModel<NavigationViewModel>()
        val reciclerViewDreamList : RecyclerView = view.findViewById(R.id.rv_home_dream_list)
        val buttonTest : Button = view.findViewById(R.id.bt_home_fragment_test_navigation)
        val buttonTestRecyclerView : Button = view.findViewById(R.id.bt_home_fragment_test_recyclerView)

        buttonTest.setOnClickListener {
            mainViewModel.interpretNavigation(MainNavigationEvent.OnNavigateToRegisterNewDreamGraph)
        }


        val imageManager = ImageManager()
        val fakeList = mutableListOf(DreamVO(imageManager.saveImageInBank(resources.getDrawable(R.drawable.iphone_dream_photo).toBitmap()), "Iphone"))
        reciclerViewDreamList.adapter = DreamAdapter(fakeList)

        //TODO remove this. That's a test
        var counter = 1
        buttonTestRecyclerView.setOnClickListener {
            reciclerViewDreamList.adapter = DreamAdapter(fakeList)
            counter++
            fakeList.add(DreamVO(imageManager.saveImageInBank(resources.getDrawable(R.drawable.iphone_dream_photo).toBitmap()), "Iphone $counter"))
        }

        //val imageManager = ImageManager()
        //val mockImageBitMap = imageManager.saveImageInBank(eiffelTowerImage.drawable.toBitmap())
    }

}