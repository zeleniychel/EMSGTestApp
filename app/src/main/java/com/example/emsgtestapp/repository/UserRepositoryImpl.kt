package com.example.emsgtestapp.repository

import com.example.emsgtestapp.api.UsersApi
import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UsersApi):UserRepository {

    override suspend fun getAll(): List<GitUser> {
        try {
            val response = api.getAll()
            if (!response.isSuccessful) {
                throw NullPointerException()
            }
            return response.body()!!
        } catch (e: IOException) {
            throw e
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun addUser() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }

    override suspend fun findUser() {
        TODO("Not yet implemented")
    }
}