package com.example.dreamorganizer.features.dreams.repository

import android.content.Context
import com.example.dreamorganizer.dao.AppDatabase
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import org.koin.androidx.compose.inject

class DreamsRepository(context: Context) {

    private val dbInstance = AppDatabase.getDatabaseAppInstance(context)

    private val daoInstance = dbInstance.dreamDao()

    suspend fun getDream(id : Int) = daoInstance.getDream(id)

    suspend fun saveDream(dream: DreamDTO){daoInstance.saveDream(dream)}

    suspend fun getAllDreams() = daoInstance.getAll()

    suspend fun deleteDream (dreamDTO: DreamDTO) {daoInstance.deleteDream(dreamDTO)}


}