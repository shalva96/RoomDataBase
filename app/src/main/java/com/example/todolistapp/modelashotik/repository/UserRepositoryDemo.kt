package com.example.todolistapp.modelashotik.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.modelashotik.storage.UserDaoDemo
import com.example.todolistapp.modelashotik.storage.UserDemo

class UserRepositoryDemo(private val userDao: UserDaoDemo) {

    val readAllData: LiveData<List<UserDemo>> = userDao.readAllDate()

    suspend fun addUser(user: UserDemo) {
        userDao.addUser(user)
    }

    suspend fun update(user: UserDemo) {
        userDao.update(user)
    }
}