package com.example.dreamorganizer.util

import android.app.Activity
import android.content.Intent
import androidx.annotation.IdRes

const val EXTRA_START_NAV_RES_ID = "EXTRA_START_NAV_RES_ID"

fun <T : Activity> Activity.navigateToNavGraph(
    entryPoint: Class<T>,
    @IdRes navGraphStartDestination: Int? = null,
    overridePendingTransition: Boolean = false,
    dreamId : Int? = null
){

    val intent = Intent(this, entryPoint)


    if (navGraphStartDestination != null){
        intent.putExtra(EXTRA_START_NAV_RES_ID, navGraphStartDestination)
    }

    if (dreamId != null){
        intent.putExtra("dream_id", dreamId)
    }

    if (overridePendingTransition){
        overridePendingTransition(0, 0)
    }

    startActivity(intent)

}