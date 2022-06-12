package com.example.dreamorganizer.presentation

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.dreamorganizer.R
import com.example.dreamorganizer.adapter.DreamAdapter
import com.example.dreamorganizer.presentation.container.interact.HomeInteractEvent
import com.example.dreamorganizer.presentation.container.interact.HomeNavigationEvent
import com.example.dreamorganizer.presentation.viewModel.HomeViewModel
import com.example.dreamorganizer.presentation.viewModel.NavigationViewModel
import com.google.android.material.imageview.ShapeableImageView
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.Exception


class HomeFragment : Fragment() {

    lateinit var recyclerViewDreamList : RecyclerView
    lateinit var tvTotalMoneyValue : TextView
    lateinit var tvRestOfTheMoneyValue : TextView
    lateinit var btnAddNewDream : Button
    lateinit var btnTotalMoney : ShapeableImageView
    private val navigationViewModel  by sharedViewModel<NavigationViewModel>()
    private val homeViewModel by sharedViewModel<HomeViewModel>()



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
        setUpListeners()
        populateDreamList()
        populateMonetaryValues()

    }

    private fun populateDreamList() {
        homeViewModel.interpret(HomeInteractEvent.GetAllDreamFromDb)
    }

    private fun populateMonetaryValues(){
        homeViewModel.interpret(HomeInteractEvent.GetMoney)
    }

    private fun initViews(view: View){
        view.let {
            recyclerViewDreamList = it.findViewById(R.id.rv_home_dream_list)
            btnAddNewDream = it.findViewById(R.id.bt_home_fragment_add_new_dream)
            btnTotalMoney = it.findViewById(R.id.bt_total_money_home)
            tvTotalMoneyValue = it.findViewById(R.id.tv_total_money_value_home)
            tvRestOfTheMoneyValue = it.findViewById(R.id.tv_rest_of_the_money_value_home)
        }
    }

    private fun setupClicks(){
        btnAddNewDream.setOnClickListener {
           navigationViewModel.interpretNavigation(HomeNavigationEvent.OnNavigateToRegisterNewDreamGraph)
        }
        btnTotalMoney.setOnClickListener {
            showChangeMoneyDialog()
        }
    }

    private fun setUpListeners(){
        homeViewModel.listOfDreams.observe(viewLifecycleOwner, Observer {
            recyclerViewDreamList.adapter = DreamAdapter(it, navigationViewModel)
        } )

        homeViewModel.totalMoney.observe(viewLifecycleOwner, Observer {
            tvTotalMoneyValue.text = getString(R.string.monetary_value_description, it.totalMoney.toInt())
            tvRestOfTheMoneyValue.text = getString(R.string.monetary_value_description, it.restOfTheMoney.toInt())
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
            Toast.makeText(context, "Monetary Value Update", Toast.LENGTH_SHORT).show()
            homeViewModel.interpret(HomeInteractEvent.AddTotalMoney(textInput.text.toString().toFloat()))

        }else if(checkValidateFields(textInput) && updateMonetaryValuesOptions == UpdateMonetaryValuesOptions.REMOVE_MONEY){
            Toast.makeText(context, "Monetary Value Update", Toast.LENGTH_SHORT).show()
            //TODO: Make business rule to display toast message saying that the account amount is less than the input amount
            homeViewModel.interpret(HomeInteractEvent.AddTotalMoney(textInput.text.toString().toFloat()))

        }
        else{
            Toast.makeText(context, "Monetary Value Not Update, check value and try again", Toast.LENGTH_LONG).show()
        }


    }


    private fun checkValidateFields(textInput: EditText) : Boolean{

        if(textInput.text!!.isBlank() || textInput.text!!.isEmpty()){
            //textInput.helperText = getString(R.string.required_text_input_warning)
            return false
        }

        if (textInput.text!!.isBlank() || textInput.text!!.isEmpty()){
            //textInputLayoutDreamValue.helperText = getString(R.string.required_text_input_warning)
            return false
        }

        if(textInput.text!!.isNotBlank() || textInput.text!!.isNotEmpty()){

            try {
                val valueConverted = textInput.text.toString().toFloat()
                return true
            }catch (e : Exception){
                //textInputLayoutDreamValue.helperText = getString(R.string.invalid_value_this_field_requires_a_numeral)
                return false
            }
        }

        else
            return true
    }

}

private enum class UpdateMonetaryValuesOptions{ADD_MONEY, REMOVE_MONEY}