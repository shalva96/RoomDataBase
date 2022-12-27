package com.example.todolistapp.model.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserDemo::class], version = 1, exportSchema = false)
abstract class UserDataBaseDemo: RoomDatabase() {

    abstract fun userDao(): UserDaoDemo

    companion object{

        @Volatile
        private var INSTANCE: UserDataBaseDemo? = null

        fun getDatabase(context: Context): UserDataBaseDemo{
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDataBaseDemo::class.java,
                    "user_database_demo"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}