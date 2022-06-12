package com.example.dreamorganizer.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.dreamorganizer.model.TotalMoneyDTO

@Dao
interface TotalMoneyDAO {
    @Insert
    suspend fun insertTotalMoney(totalMoney: TotalMoneyDTO)

    @Query("SELECT * FROM total_money")
    suspend fun getMoney() : List<TotalMoneyDTO>

    @Update
    suspend fun updateMoney(totalMoney: TotalMoneyDTO)
}