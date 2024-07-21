package com.example.emsgtestapp.repository

import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserByLogin(login: String): GitUser
    suspend fun findUser(login: String): List<User>
}