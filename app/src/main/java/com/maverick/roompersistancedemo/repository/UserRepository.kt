package com.maverick.roompersistancedemo.repository

import androidx.lifecycle.LiveData
import com.maverick.roompersistancedemo.data.User
import com.maverick.roompersistancedemo.data.UserDao

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

}