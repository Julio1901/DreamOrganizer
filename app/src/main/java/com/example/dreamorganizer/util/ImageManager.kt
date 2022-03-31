package com.example.dreamorganizer.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream

class ImageManager {

    //Receive image that will be sent as BitMap
    fun saveImageInBank(imageBitmap: Bitmap) : ByteArray{

        val stream = ByteArrayOutputStream()
        imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        val imagemBytes = stream.toByteArray()

        return imagemBytes
    }

    //Takes image from database and converts to display
    fun convertBankImageToDisplay(bankImage : ByteArray) : Bitmap{

        val imageStream = ByteArrayInputStream(bankImage)
        val imageBitmap = BitmapFactory.decodeStream(imageStream)

        return imageBitmap
    }

}