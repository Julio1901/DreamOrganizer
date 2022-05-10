package com.example.dreamorganizer.features.dreams.dao

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.dreamorganizer.features.dreams.model.DreamDTO

interface DreamDAO {

    @Query("SELECT * FROM dreams")
    fun getAll(): List<DreamDTO>

    @Query("SELECT * FROM dreams WHERE id LIKE :id")
    fun getDream(id: Int) : DreamDTO

    @Insert
    fun saveDream(dreamDTO: DreamDTO)

    @Delete
    fun deleteDream(dreamDTO: DreamDTO)

}