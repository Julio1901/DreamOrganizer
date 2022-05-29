package com.example.dreamorganizer.features.dreams.presentation.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import androidx.core.view.isVisible
import com.example.dreamorganizer.R
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamContainerNavigationEvent
import com.example.dreamorganizer.features.dreams.presentation.container.DreamContainerViewModel
import com.example.dreamorganizer.features.dreams.presentation.container.interact.DreamsInteract
import com.example.dreamorganizer.viewModel.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import java.lang.Exception

private const val REQUEST_CODE = 100

class RegisterNewDreamFragment : Fragment() {

    lateinit var textInputLayoutDreamName : TextInputLayout
    lateinit var textInputDreamName : TextInputEditText
    lateinit var textInputLayoutDreamValue : TextInputLayout
    lateinit var textInputDreamValue : TextInputEditText
    lateinit var ivDisplayDreamImage : ImageView
    lateinit var ivButtonCameraSelectImageFromGallery : ImageView
    lateinit var btnSaveDream : Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_new_dream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupListeners()

        val mainViewModel  by sharedViewModel<MainViewModel>()
        val dreamContainerViewModel by sharedViewModel<DreamContainerViewModel>()




        //TODO: Replace this to call function by interaction
        btnSaveDream.setOnClickListener {


            //TODO remove this mock test
            //val mockDream = DreamDTO(id = 0 ,name = "Iphone ", value = 7000.0F, totalMoneyReserved = 0F ,image = null)



            if(checkValidateFields()){
                val newDreamToSave = DreamDTO(id= 0,
                    name = textInputDreamName.text.toString(),
                    value= textInputDreamValue.text.toString().toFloat(),
                    totalMoneyReserved = 0F ,image = null
                )


                //mainViewModel.interpret(DreamsInteract.AddNewDream(newDreamToSave))
                //dreamContainerViewModel.interpretNavigation(DreamContainerNavigationEvent.NavigateToHome)
            }

        }





    }

    private fun initViews(view: View){
        view.let {
            textInputLayoutDreamName = it.findViewById(R.id.tvl_dream_name_add_new_dream)
            textInputDreamName = it.findViewById(R.id.tied_dream_name_add_new_dream)
            textInputLayoutDreamValue = it.findViewById(R.id.tvl_dream_value_add_new_dream)
            textInputDreamValue = it.findViewById(R.id.tied_dream_value_add_new_dream)
            ivDisplayDreamImage = it.findViewById(R.id.iv_display_dream_image_add_new_dream)
            ivButtonCameraSelectImageFromGallery = it.findViewById(R.id.iv_button_camera_add_new_dream)
            btnSaveDream = it.findViewById(R.id.bt_save_add_new_dream)
        }
    }


    private fun setupListeners(){
        textInputDreamName.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                textInputLayoutDreamName.defaultHintTextColor = getColorStateList(requireContext(), R.color.old_gold)
                textInputLayoutDreamName.helperText = getString(R.string.default_blank_value)
            }else
                textInputLayoutDreamName.defaultHintTextColor = getColorStateList(requireContext(), R.color.white)
        }

        textInputDreamValue.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                textInputLayoutDreamValue.defaultHintTextColor =getColorStateList(requireContext(), R.color.old_gold)
                textInputLayoutDreamValue.hint = resources.getString(R.string.dream_value)
                textInputLayoutDreamValue.helperText = getString(R.string.default_blank_value)
            }else{
                textInputLayoutDreamValue.defaultHintTextColor = getColorStateList(requireContext(), R.color.white)
                textInputLayoutDreamValue.hint = resources.getString(R.string.dream_value_hint)
            }
        }

        ivButtonCameraSelectImageFromGallery.setOnClickListener {
            openGalleryForImage()
        }


    }

    private fun checkValidateFields() : Boolean{

        if(textInputDreamName.text!!.isBlank() || textInputDreamName.text!!.isEmpty()){
            textInputLayoutDreamName.helperText = getString(R.string.required_text_input_warning)
            return false
        }

        if (textInputDreamValue.text!!.isBlank() || textInputDreamValue.text!!.isEmpty()){
            textInputLayoutDreamValue.helperText = getString(R.string.required_text_input_warning)
            return false
        }

        if(textInputDreamValue.text!!.isNotBlank() || textInputDreamValue.text!!.isNotEmpty()){

            try {
                val valueConverted = textInputDreamValue.text.toString().toFloat()
                return true
            }catch (e : Exception){
                textInputLayoutDreamValue.helperText = getString(R.string.invalid_value_this_field_requires_a_numeral)
                return false
            }
        }

        else
            return true
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            ivDisplayDreamImage.isVisible = true
            ivDisplayDreamImage.setImageURI(data?.data)
        }
    }




}