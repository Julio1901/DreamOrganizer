package com.example.dreamorganizer.util

import android.app.Activity
import androidx.annotation.IdRes
import androidx.fragment.app.Fragment

fun <T : Activity> Fragment.navigateToNavGraph (
    entryPoint: Class<T>,
    @IdRes navGraphStartDestination: Int? = null,
    overridePendingTransition: Boolean = false,
    dreamId : Int? = null
){
    requireActivity().navigateToNavGraph(
        entryPoint,
        navGraphStartDestination,
        overridePendingTransition,
        dreamId
        )

}