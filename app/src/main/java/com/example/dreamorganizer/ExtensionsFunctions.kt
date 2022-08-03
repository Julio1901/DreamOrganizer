package com.example.dreamorganizer

import android.widget.ImageButton
import android.widget.TextView
import org.koin.core.component.getScopeId

fun TextView.changeDisplayVisibility(){
     val PASSWORD_INPUT_TYPE = 129
     val NORMAL_INPUT_TYPE_VISIBLE = 0

    if (this.inputType == PASSWORD_INPUT_TYPE){
        this.inputType = NORMAL_INPUT_TYPE_VISIBLE
    }else{
        this.inputType = PASSWORD_INPUT_TYPE
    }
}


