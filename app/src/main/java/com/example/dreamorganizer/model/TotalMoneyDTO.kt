package com.example.dreamorganizer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "total_money")
data class TotalMoneyDTO(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name= "total_money")
    var totalMoney: Float,
    @ColumnInfo(name="rest_of_the_money")
    var restOfTheMoney: Float
)
