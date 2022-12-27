package com.example.todolistapp.model.repository

import androidx.lifecycle.LiveData
import com.example.todolistapp.model.storage.UserDaoDemo
import com.example.todolistapp.model.storage.UserDemo

class UserRepositoryDemo(private val userDao: UserDaoDemo) {

    val readAllData: LiveData<List<UserDemo>> = userDao.readAllDate()

    suspend fun addUser(user: UserDemo) {
        userDao.addUser(user)
    }

    suspend fun update(user: UserDemo) {
        userDao.update(user)
    }

    suspend fun delete(user: UserDemo) {
        userDao.deleteUser(user = user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }
}