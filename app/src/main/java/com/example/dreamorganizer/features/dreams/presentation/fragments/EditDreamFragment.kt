package com.example.dreamorganizer.features.dreams.presentation.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.presentation.container.DreamContainerViewModel
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamContainerNavigationEvent
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
import com.example.dreamorganizer.features.dreams.presentation.container.interact.TypeForCalculation
import com.example.dreamorganizer.features.dreams.viewModel.DreamFeaturesViewModel
import com.example.dreamorganizer.presentation.viewModel.MainViewModel
import com.example.dreamorganizer.util.ImageManager
import com.google.android.material.imageview.ShapeableImageView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class EditDreamFragment : Fragment() {

    private lateinit var dreamImage: ShapeableImageView
    private lateinit var dreamName: TextView
    private lateinit var totalMoneyReserved: TextView
    private lateinit var totalMoneyNeeded: TextView
    private lateinit var btnDeleteDream: ShapeableImageView
    private val dreamFeatureViewModel by sharedViewModel<DreamFeaturesViewModel>()
    private val navigateViewModel by sharedViewModel<DreamContainerViewModel>()
    private val mainViewModel by sharedViewModel<MainViewModel>()
    private lateinit var  imageManager: ImageManager
    private lateinit var btnPlusMoneyReserved: Button
    private lateinit var btnSubtractMoneyReserved: Button
    private lateinit var btnPlusTotalValue: Button
    private lateinit var btnSubtractTotalValue: Button
    private lateinit var btnBackToHome: ShapeableImageView
    private val EXTRAS_DREAM_ID: String = "dream_id"



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
        setUpListeners()
    }



    private fun initViews(view: View){
        view.let {
            dreamImage = it.findViewById(R.id.iv_display_dream_image_add_new_dream)
            dreamName = it.findViewById(R.id.tv_dream_name_edit_dream)
            totalMoneyReserved = it.findViewById(R.id.tv_total_money_reserved_value_edit_dream)
            totalMoneyNeeded = it.findViewById(R.id.tv_total_money_needed_value_edit_dream)
            btnDeleteDream = it.findViewById(R.id.bt_delete_dream_edit_dream)
            btnPlusMoneyReserved = it.findViewById(R.id.bt_plus_money_reserved_edit_dream)
            btnSubtractMoneyReserved = it.findViewById(R.id.bt_subtract_money_reserved_edit_dream)
            btnPlusTotalValue = it.findViewById(R.id.bt_plus_total_value_edit_dream)
            btnSubtractTotalValue = it.findViewById(R.id.bt_subtract_total_value_edit_dream)
            btnBackToHome = it.findViewById(R.id.bt_back_to_home_edit_dream)
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

        dreamFeatureViewModel.toastMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, getString(it), Toast.LENGTH_SHORT).show()
        })

    }

    private fun setUpListeners() {
        btnPlusMoneyReserved.setOnClickListener {
            dreamFeatureViewModel.interpret(DreamsInteract.PlusOneMoreToTotalValue(TypeForCalculation.MONEY_RESERVED))
        }

        btnPlusTotalValue.setOnClickListener {
            dreamFeatureViewModel.interpret(DreamsInteract.PlusOneMoreToTotalValue(TypeForCalculation.TOTAL_VALUE))
        }

        btnSubtractMoneyReserved.setOnClickListener {
            dreamFeatureViewModel.interpret(DreamsInteract.SubtractOneFromTotalValue(TypeForCalculation.MONEY_RESERVED))
        }

        btnSubtractTotalValue.setOnClickListener {
            dreamFeatureViewModel.interpret(DreamsInteract.SubtractOneFromTotalValue(TypeForCalculation.TOTAL_VALUE))
        }

        btnBackToHome.setOnClickListener {
            if(dreamFeatureViewModel.dreamHasEdited){
                showHandleWithEditedDreamAlertDialog()
            }else{
                navigateViewModel.interpretNavigation(DreamContainerNavigationEvent.NavigateToHome)
            }

        }

        btnDeleteDream.setOnClickListener {
            //TODO: Validate before delete with alert dialog
            dreamFeatureViewModel.interpret(DreamsInteract.DeleteDream)
            //TODO: return dream money from total value (make this logic in viewModel)
            navigateViewModel.interpretNavigation(DreamContainerNavigationEvent.NavigateToHome)
        }

    }

    private fun showHandleWithEditedDreamAlertDialog() {
            val dialog = AlertDialog.Builder(requireActivity())
            val inflater = requireActivity().layoutInflater

            //val view: View = inflater.inflate(R.layout.dialog_change_monetary_value, null)

            dialog.setCancelable(false)
                .setPositiveButton(R.string.save_changes, DialogInterface.OnClickListener { dialog, id ->
                    dreamFeatureViewModel.interpret(DreamsInteract.UpdateDream)
                    dreamFeatureViewModel.interpret(DreamsInteract.UpdateUserTotalMoney)
                    navigateViewModel.interpretNavigation(DreamContainerNavigationEvent.NavigateToHome)
                }
                )
                .setNegativeButton(R.string.discart_changes,
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.cancel()
                        navigateViewModel.interpretNavigation(DreamContainerNavigationEvent.NavigateToHome)
                    })


            dialog.show()

    }


    private fun prepareScreenWithPopulateInfo(){
        val dreamId = requireActivity().intent.extras.let {
            it?.get(EXTRAS_DREAM_ID)
        }
        dreamFeatureViewModel.interpret(DreamsInteract.ChangeSelectedDreamId(dreamId))
        dreamFeatureViewModel.interpret(DreamsInteract.GetTotalUserMoney)
    }



}