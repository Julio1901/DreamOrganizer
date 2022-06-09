package com.example.dreamorganizer.features.dreams.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dreams")
data class DreamDTO(
    @PrimaryKey (autoGenerate = true) val id: Int,
    var name: String,
    var value: Float,
    @ColumnInfo(name = "total_money_reserved")
    var totalMoneyReserved: Float,
    val image: ByteArray?
)
