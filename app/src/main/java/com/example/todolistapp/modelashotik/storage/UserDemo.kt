package com.example.todolistapp.modelashotik.storage

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "demo_table")
data class UserDemo (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String
)