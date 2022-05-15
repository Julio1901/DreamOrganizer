package com.example.dreamorganizer.features.dreams.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dreams")
data class DreamDTO(
    @PrimaryKey (autoGenerate = true) val id: Int,
    val name: String?,
    val value: Float?,
    @ColumnInfo(name = "total_money_reserved")
    val totalMoneyReserved: Float?,
    val image: ByteArray?
)
