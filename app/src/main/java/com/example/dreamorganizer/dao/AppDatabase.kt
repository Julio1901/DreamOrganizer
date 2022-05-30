package com.example.dreamorganizer.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dreamorganizer.features.dreams.dao.DreamDAO
import com.example.dreamorganizer.features.dreams.model.DreamDTO

@Database(entities = [DreamDTO :: class], version = 2)
abstract class AppDatabase : RoomDatabase(){

    abstract fun dreamDao(): DreamDAO

    companion object{
        fun getDatabaseAppInstance(context : Context) : AppDatabase{
            return Room.databaseBuilder(context, AppDatabase::class.java, "dream-organizer-database").build()
        }
    }

}