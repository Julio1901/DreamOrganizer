package com.example.dreamorganizer.repository

import android.content.Context
import com.example.dreamorganizer.dao.AppDatabase
import com.example.dreamorganizer.model.TotalMoneyDTO

class TotalMoneyRepository(context: Context) {

    private val dbInstance = AppDatabase.getDatabaseAppInstance(context)
    private val daoInstance = dbInstance.totalMoneyDao()

    suspend fun insertTotalMoney(totalMoney: TotalMoneyDTO){daoInstance.insertTotalMoney(totalMoney)}

    suspend fun getMoney() = daoInstance.getMoney()

    suspend fun updateMoney(totalMoney: TotalMoneyDTO){daoInstance.updateMoney(totalMoney)}

}