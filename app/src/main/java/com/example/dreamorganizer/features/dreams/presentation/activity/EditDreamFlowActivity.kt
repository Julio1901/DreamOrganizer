package com.example.dreamorganizer.features.dreams.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dreamorganizer.R

class EditDreamFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_dream_flow)


        val id = intent.extras!!.get("dream_id")
        Log.e("chegou", id.toString())


    }
}