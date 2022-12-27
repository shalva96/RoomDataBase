package com.example.todolistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.model.repository.UserRepositoryDemo
import com.example.todolistapp.model.storage.UserDataBaseDemo
import com.example.todolistapp.model.storage.UserDemo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModelDemo(application: Application): AndroidViewModel(application) {
    val readAllDate: LiveData<List<UserDemo>>
    private val userRepository: UserRepositoryDemo

    init {
        val userDao = UserDataBaseDemo.getDatabase(application).userDao()
        userRepository = UserRepositoryDemo(userDao)
        readAllDate = userRepository.readAllData
    }
    fun addUser(user:UserDemo) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user = user)
        }
    }

    fun update(user: UserDemo) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.update(user = user)
        }
    }

    fun delete(user: UserDemo) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.delete(user)
        }
    }

    fun deleteAllUser() {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.deleteAllUser()
        }
    }




}