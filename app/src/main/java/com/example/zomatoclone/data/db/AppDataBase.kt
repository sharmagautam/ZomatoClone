package com.example.zomatoclone.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

abstract class AppDataBase : RoomDatabase() {

    companion object{
        @Volatile
        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance?: synchronized(LOCK){
            instance?: buildDataBase(context).also {
                instance = it
            }
        }

        private fun buildDataBase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    AppDataBase::class.java,
                    "MyDataBase.db"
                ).build()
    }
}