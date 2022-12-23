package com.example.todolistapp.viewmodelashotik

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapp.modelashotik.repository.UserRepositoryDemo
import com.example.todolistapp.modelashotik.storage.UserDataBaseDemo
import com.example.todolistapp.modelashotik.storage.UserDemo
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


}