package com.example.emsgtestapp.repository

import com.example.emsgtestapp.api.UsersApi
import com.example.emsgtestapp.error.ApiError
import com.example.emsgtestapp.error.NetworkError
import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User
import java.io.IOException
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val api: UsersApi
) : UserRepository {

    override suspend fun getUsers(): List<User> {
        try {
            val response = api.getUsers()
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError()
        }
    }

    override suspend fun getUserById(login: String): GitUser {
        try {
            val response = api.getUserById(login)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body() ?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError()
        }
    }

    override suspend fun addUser() {
        TODO("Not yet implemented")
    }

    override suspend fun deleteUser() {
        TODO("Not yet implemented")
    }
}