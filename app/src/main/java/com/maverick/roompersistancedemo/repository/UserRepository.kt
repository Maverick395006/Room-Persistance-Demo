package com.maverick.roompersistancedemo.repository

import androidx.lifecycle.LiveData
import com.maverick.roompersistancedemo.data.model.User
import com.maverick.roompersistancedemo.data.db.UserDao
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDao: UserDao) {

    val readAllData: LiveData<List<User>> = userDao.readAllData()

    suspend fun addUser(user: User) {
        userDao.addUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }

    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

    fun searchInDatabase(searchQuery: String): Flow<List<User>> {
        return userDao.searchInDatabase(searchQuery)
    }

}