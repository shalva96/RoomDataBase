package com.example.todolistapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.model.repository.UserRepository
import com.example.todolistapp.model.storage.UserDataBase
import com.example.todolistapp.model.storage.UserName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<UserName>>
    private val repository: UserRepository


    init {
        val userDao = UserDataBase.getDataBase(application).userDao()
        repository = UserRepository(userDao = userDao)
        readAllData = repository.readAllData
    }

    fun addUser(userName: UserName){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(userName = userName)
        }
    }
}