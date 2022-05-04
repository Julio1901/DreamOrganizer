package com.example.dreamorganizer.features

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources.getColorStateList
import com.example.dreamorganizer.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout


class RegisterNewDreamFragment : Fragment() {

    lateinit var textInputLayoutDreamName : TextInputLayout
    lateinit var textInputDreamName : TextInputEditText
    lateinit var textInputLayoutDreamValue : TextInputLayout
    lateinit var textInputDreamValue : TextInputEditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_register_new_dream, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews(view)
        setupDefaultViewStates()
        setupListeners()
    }

    private fun initViews(view: View){
        view.let {
            textInputLayoutDreamName = it.findViewById(R.id.tvl_dream_name_add_new_dream)
            textInputDreamName= it.findViewById(R.id.tied_dream_name_add_new_dream)
            textInputLayoutDreamValue = it.findViewById(R.id.tvl_dream_value_add_new_dream)
            textInputDreamValue= it.findViewById(R.id.tied_dream_value_add_new_dream)
        }
    }

    private fun setupDefaultViewStates(){
        textInputLayoutDreamName.isHelperTextEnabled = false
        textInputLayoutDreamValue.isHelperTextEnabled = false
    }

    private fun setupListeners(){
        textInputDreamName.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if (hasFocus){
                textInputLayoutDreamName.defaultHintTextColor = getColorStateList(requireContext(), R.color.old_gold)
            }else
                textInputLayoutDreamName.defaultHintTextColor = getColorStateList(requireContext(), R.color.white)
        }

        textInputDreamValue.onFocusChangeListener = View.OnFocusChangeListener { _, hasFocus ->
            if(hasFocus){
                textInputLayoutDreamValue.defaultHintTextColor =getColorStateList(requireContext(), R.color.old_gold)
                textInputLayoutDreamValue.hint = resources.getString(R.string.dream_value)
            }else{
                textInputLayoutDreamValue.defaultHintTextColor = getColorStateList(requireContext(), R.color.white)
                textInputLayoutDreamValue.hint = resources.getString(R.string.dream_value_hint)
            }
        }
    }

}