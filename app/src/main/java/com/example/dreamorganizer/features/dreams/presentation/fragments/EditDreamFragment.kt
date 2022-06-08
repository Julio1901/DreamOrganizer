package com.example.dreamorganizer.features.dreams.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
import com.example.dreamorganizer.features.dreams.viewModel.DreamFeaturesViewModel
import com.example.dreamorganizer.util.ImageManager
import com.google.android.material.imageview.ShapeableImageView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class EditDreamFragment : Fragment() {

    private lateinit var dreamImage: ShapeableImageView
    private lateinit var dreamName: TextView
    private lateinit var totalMoneyReserved : TextView
    private lateinit var totalMoneyNeeded: TextView
    private lateinit var btnChangeValue: Button
    private lateinit var btnDeleteDream: ShapeableImageView
    private val dreamFeatureViewModel by sharedViewModel<DreamFeaturesViewModel>()
    private lateinit var  imageManager : ImageManager
    private val EXTRAS_DREAM_ID : String = "dream_id"



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_edit_dream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setUpObservers()
        prepareScreenWithPopulateInfo()
    }


    private fun initViews(view: View){
        view.let {
            dreamImage = it.findViewById(R.id.iv_display_dream_image_add_new_dream)
            dreamName = it.findViewById(R.id.tv_dream_name_edit_dream)
            totalMoneyReserved = it.findViewById(R.id.tv_total_money_reserved_value_edit_dream)
            totalMoneyNeeded = it.findViewById(R.id.tv_total_money_needed_value_edit_dream)
            btnChangeValue = it.findViewById(R.id.bt_change_value_edit_dream)
            btnDeleteDream = it.findViewById(R.id.bt_delete_dream_edit_dream)
        }

        imageManager = ImageManager()
    }

    private fun setUpObservers(){
        dreamFeatureViewModel.selectedDream.observe(viewLifecycleOwner, Observer {

            it.image?.let {
                dreamImage.setImageBitmap(imageManager.convertBankImageToDisplay(it))
            }

            dreamName.text = it.name
            totalMoneyNeeded.text = it.value.toString()
            totalMoneyReserved.text = it.totalMoneyReserved.toString()


        })

    }


    private fun prepareScreenWithPopulateInfo(){
        val dreamId = requireActivity().intent.extras.let {
            it?.get(EXTRAS_DREAM_ID)
        }
        dreamFeatureViewModel.interpret(DreamsInteract.ChangeSelectedDreamId(dreamId))
    }



}