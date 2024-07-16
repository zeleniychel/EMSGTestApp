package com.example.emsgtestapp.repository

import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserById(login: String): GitUser
    suspend fun addUser()
    suspend fun deleteUser()
}