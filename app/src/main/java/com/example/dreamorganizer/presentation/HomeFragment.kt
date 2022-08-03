package com.example.dreamorganizer.presentation

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.res.ColorStateList
import android.os.Bundle
import android.text.InputType
import android.text.InputType.TYPE_TEXT_VARIATION_PASSWORD
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.compose.ui.graphics.Color
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.adapter.DreamAdapter
import com.example.dreamorganizer.changeDisplayVisibility
import com.example.dreamorganizer.features.dreams.viewModel.DreamFeaturesViewModel
import com.example.dreamorganizer.presentation.container.interact.ChangeTotalMoneyValueOptions
import com.example.dreamorganizer.presentation.container.interact.MainInteractEvent
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.MainViewModel
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.google.android.material.imageview.ShapeableImageView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.core.component.getScopeId
import org.koin.core.component.getScopeName
import java.lang.Exception


class HomeFragment : Fragment() {

    lateinit var recyclerViewDreamList : RecyclerView
    lateinit var tvTotalMoneyValue : TextView
    lateinit var tvRestOfTheMoneyValue : TextView
    lateinit var btnAddNewDream : Button
    lateinit var btnTotalMoney : ShapeableImageView
    lateinit var btnVisibilityTotalMoney: ImageButton
    lateinit var btnVisibilityRestOfTheMoney: ImageButton
    private val navigationViewModel  by sharedViewModel<NavigationViewModel>()
    private val mainViewModel by sharedViewModel<MainViewModel>()

    private val icVisibilityOnIcon = R.drawable.ic_visibility_on
    private val icVisibilityOffIcon = R.drawable.ic_visibility_off

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners()
        setUpObservers()
        populateDreamList()
        populateMonetaryValues()
       prepareHomeScreenDefaultStates()
        //progressBarDreamImage.setProgressTintList(ColorStateList.valueOf(resources.getColor(R.color.graphite)))
    }

    private fun prepareHomeScreenDefaultStates(){
       btnVisibilityTotalMoney.setImageResource(R.drawable.ic_visibility_off)
    }

    private fun populateDreamList() {
        mainViewModel.interpret(MainInteractEvent.GetAllDreamFromDb)
    }

    private fun populateMonetaryValues(){
        mainViewModel.interpret(MainInteractEvent.GetMoney)
    }

    private fun initViews(view: View){
        view.let {
            recyclerViewDreamList = it.findViewById(R.id.rv_home_dream_list)
            btnAddNewDream = it.findViewById(R.id.bt_home_fragment_add_new_dream)
            btnTotalMoney = it.findViewById(R.id.bt_total_money_home)
            tvTotalMoneyValue = it.findViewById(R.id.tv_total_money_value_home)
            tvRestOfTheMoneyValue = it.findViewById(R.id.tv_rest_of_the_money_value_home)
            btnVisibilityTotalMoney = it.findViewById(R.id.ib_visibility_total_money)
            btnVisibilityRestOfTheMoney = it.findViewById(R.id.ib_visibility_rest_of_the_money)
        }
    }

    private fun setupListeners(){
        btnAddNewDream.setOnClickListener {
           navigationViewModel.interpretNavigation(HomeNavigationEvent.OnNavigateToRegisterNewDreamGraph)
        }
        btnTotalMoney.setOnClickListener {
            showChangeMoneyDialog()
        }

        btnVisibilityTotalMoney.setOnClickListener {
            mainViewModel.interpret(MainInteractEvent.ChangeVisibilityIconTotalMoney(it.id, it.isVisible))
            tvTotalMoneyValue.changeDisplayVisibility()
        }

        btnVisibilityRestOfTheMoney.setOnClickListener {
            mainViewModel.interpret(MainInteractEvent.ChangeVisibilityIconRestOfTheMoney(it.id, it.isVisible))
            tvRestOfTheMoneyValue.changeDisplayVisibility()
        }
    }

    //TODO: Replace it
    //Not working, change this latter
    private fun handleWithVisibilityIcon(btn : ImageButton) {
        if (btn.isVisible){
            btn.setImageResource(R.drawable.ic_visibility_on)
        }else {
            btn.setImageResource(R.drawable.ic_visibility_off)
        }
    }


    private fun setUpObservers(){
        mainViewModel.listOfDreams.observe(viewLifecycleOwner, Observer {
            recyclerViewDreamList.adapter = DreamAdapter(it, navigationViewModel)
        } )

        mainViewModel.totalMoney.observe(viewLifecycleOwner, Observer {
            tvTotalMoneyValue.text = getString(R.string.monetary_value_description, it.totalMoney.toInt())
            tvRestOfTheMoneyValue.text = getString(R.string.monetary_value_description, it.restOfTheMoney.toInt())
        })

        mainViewModel.alertDialogMessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(context, getString(it), Toast.LENGTH_LONG).show()
        })


        mainViewModel.visibilityIconTotalMoney.observe(viewLifecycleOwner, Observer {
            val btnId = it.btnId
            val btn: ImageButton? = view?.findViewById(btnId)
            if (btn != null){
               //btn.isVisible = it.isVisible
                handleWithVisibilityIcon(btn)
            }
        })

        mainViewModel.visibilityIconRestOfTheMoney.observe(viewLifecycleOwner, Observer {
            val btnId = it.btnId
            val btn: ImageButton? = view?.findViewById(btnId)

            if (btn != null){
                //btn.isVisible = it.isVisible
                handleWithVisibilityIcon(btn)
            }
        })

    }

    private fun showChangeMoneyDialog() {
        val dialog = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_change_monetary_value, null)
        val edInputMonetaryValue : EditText = view.findViewById(R.id.et_input_monetary_value_alert_dialog)

            dialog.setView(view).setCancelable(false)
            .setPositiveButton(R.string.input_new_value, DialogInterface.OnClickListener { dialog, id ->
                handleWithChangeMoneyDialogData(edInputMonetaryValue, UpdateMonetaryValuesOptions.ADD_MONEY)
              }
                ).setNeutralButton(R.string.remove_money,
                    DialogInterface.OnClickListener { dialog, id ->
                        showRemoveMoneyDialog()
                    })
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })

        dialog.show()
        }


    private fun showRemoveMoneyDialog() {
        val dialog = AlertDialog.Builder(requireActivity())
        val inflater = requireActivity().layoutInflater
        val view: View = inflater.inflate(R.layout.dialog_change_monetary_value, null)
        val edInputMonetaryValue : EditText = view.findViewById(R.id.et_input_monetary_value_alert_dialog)

        dialog.setView(view).setCancelable(false)
            .setPositiveButton(R.string.remove_money, DialogInterface.OnClickListener { dialog, id ->
                handleWithChangeMoneyDialogData(edInputMonetaryValue, UpdateMonetaryValuesOptions.REMOVE_MONEY)
            }
            )
            .setNegativeButton(R.string.cancel,
                DialogInterface.OnClickListener { dialog, id ->
                    dialog.cancel()
                })
        dialog.show()
    }



    private fun handleWithChangeMoneyDialogData(textInput: EditText, updateMonetaryValuesOptions: UpdateMonetaryValuesOptions){
        if(checkValidateFields(textInput) && updateMonetaryValuesOptions == UpdateMonetaryValuesOptions.ADD_MONEY){
            mainViewModel.interpret(MainInteractEvent.ChangeTotalMoneyValue(textInput.text.toString().toFloat(), ChangeTotalMoneyValueOptions.ADD_VALUE))

        }else if(checkValidateFields(textInput) && updateMonetaryValuesOptions == UpdateMonetaryValuesOptions.REMOVE_MONEY){
            mainViewModel.interpret(MainInteractEvent.ChangeTotalMoneyValue(textInput.text.toString().toFloat(), ChangeTotalMoneyValueOptions.REMOVE_VALUE))

        }
        else{
            mainViewModel.interpret(MainInteractEvent.ChangeAlertDialogMessage(R.string.monetary_value_not_update_error_message))
        }
    }


    private fun checkValidateFields(textInput: EditText) : Boolean{

        if(textInput.text!!.isBlank() || textInput.text!!.isEmpty()){
            return false
        }

        if (textInput.text!!.isBlank() || textInput.text!!.isEmpty()){
            return false
        }

        if(textInput.text!!.isNotBlank() || textInput.text!!.isNotEmpty()){

            try {
                val valueConverted = textInput.text.toString().toFloat()
                return true
            }catch (e : Exception){
               //TODO: Log error here
                return false
            }
        }

        else
            return true
    }



}

private enum class UpdateMonetaryValuesOptions{ADD_MONEY, REMOVE_MONEY}