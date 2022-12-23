package com.example.todolistapp.model.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserName(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String
)