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
import com.example.dreamorganizer.features.dreams.presentation.activity.EditDreamFlowActivity
import com.example.dreamorganizer.model.DreamVO
import com.example.dreamorganizer.presentation.container.interact.MainNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.example.dreamorganizer.util.ImageManager
import com.example.dreamorganizer.util.navigateToNavGraph
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class HomeFragment : Fragment() {

    lateinit var reciclerViewDreamList : RecyclerView
    lateinit var btnAddNewDream : Button
    private val mainViewModel  by sharedViewModel<NavigationViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupClicks()



        val imageManager = ImageManager()
        val fakeList = mutableListOf(DreamVO(imageManager.saveImageInBank(resources.getDrawable(R.drawable.iphone_dream_photo).toBitmap()), "Iphone"))
        reciclerViewDreamList.adapter = DreamAdapter(fakeList)

    }

    private fun initViews(view: View){
        view.let {
            reciclerViewDreamList = it.findViewById(R.id.rv_home_dream_list)
            btnAddNewDream = it.findViewById(R.id.bt_home_fragment_add_new_dream)
        }
    }

    private fun setupClicks(){
        btnAddNewDream.setOnClickListener {
            mainViewModel.interpretNavigation(MainNavigationEvent.OnNavigateToRegisterNewDreamGraph)
        }

    }

}