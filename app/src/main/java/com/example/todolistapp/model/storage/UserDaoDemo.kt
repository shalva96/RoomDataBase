package com.example.todolistapp.model.storage

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDaoDemo {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: UserDemo)


    @Query("SELECT * FROM demo_table ORDER BY id ASC")
    fun readAllDate(): LiveData<List<UserDemo>>

    @Update
    suspend fun update(user: UserDemo)

    @Delete
    suspend fun deleteUser(user: UserDemo)

    @Query("DELETE FROM demo_table")
    suspend fun deleteAllUser()

}