package com.example.dreamorganizer.features.dreams.dao

import androidx.room.*
import com.example.dreamorganizer.features.dreams.model.DreamDTO
import com.example.dreamorganizer.model.TotalMoneyDTO

@Dao
interface DreamDAO {

    @Query("SELECT * FROM dreams")
    suspend fun getAll(): List<DreamDTO>

    @Query("SELECT * FROM dreams WHERE id LIKE :id")
    suspend fun getDream(id: Int) : DreamDTO

    @Insert
    suspend fun saveDream(dreamDTO: DreamDTO)

    @Delete
    suspend fun deleteDream(dreamDTO: DreamDTO)

    @Update
    suspend fun updateDream(dreamDTO: DreamDTO)

}