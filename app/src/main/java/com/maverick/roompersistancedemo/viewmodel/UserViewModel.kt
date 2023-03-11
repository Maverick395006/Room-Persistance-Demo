package com.maverick.roompersistancedemo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.maverick.roompersistancedemo.data.model.User
import com.maverick.roompersistancedemo.data.db.UserDatabase
import com.maverick.roompersistancedemo.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {

    val readAllData: LiveData<List<User>>
    private val userRepository: UserRepository

    init {
        val userDao = UserDatabase.getDatabase(application).userDao()
        userRepository = UserRepository(userDao)
        readAllData = userRepository.readAllData
    }

    fun addUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.addUser(user)
        }
    }

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userRepository.updateUser(user)
        }
    }

}