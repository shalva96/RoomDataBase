package com.example.todolistapp.model.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.model.storage.UserDao
import com.example.todolistapp.model.storage.UserName

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<UserName>> = userDao.readAllData()

    suspend fun addUser(userName: UserName) {
        userDao.addUser(userName = userName)
    }
}