package com.example.emsgtestapp.repository

import androidx.lifecycle.LiveData
import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User

interface UserRepository {
suspend fun getAll():List<GitUser>
suspend fun addUser()
suspend fun deleteUser()
suspend fun findUser()
}