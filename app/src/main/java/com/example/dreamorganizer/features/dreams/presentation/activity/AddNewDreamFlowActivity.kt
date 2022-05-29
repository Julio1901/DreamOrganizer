package com.example.dreamorganizer.features.dreams.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.dreamorganizer.R
import com.example.dreamorganizer.util.EXTRA_START_NAV_RES_ID

class AddNewDreamFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_new_dream_flow)

        //TODO: Check if it has necessary
//        intent?.extras?.getInt(EXTRA_START_NAV_RES_ID)
//            ?.takeIf { it > 0 }
//            ?.let { findNavController(it).navigate(it) }
    }
}