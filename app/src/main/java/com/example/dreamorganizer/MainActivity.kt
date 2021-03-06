package com.example.dreamorganizer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.dreamorganizer.util.EXTRA_START_NAV_RES_ID

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        intent?.extras?.getInt(EXTRA_START_NAV_RES_ID)
            ?.takeIf { it > 0 }
            ?.let { findNavController(it).navigate(it) }
    }
}