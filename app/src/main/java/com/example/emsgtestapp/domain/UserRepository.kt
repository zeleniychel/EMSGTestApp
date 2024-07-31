package com.example.emsgtestapp.domain

import com.example.emsgtestapp.data.model.GitUser
import com.example.emsgtestapp.data.model.User

interface UserRepository {
    suspend fun getUsers(): List<User>
    suspend fun getUserByLogin(login: String): GitUser
    suspend fun findUser(login: String): List<User>
}