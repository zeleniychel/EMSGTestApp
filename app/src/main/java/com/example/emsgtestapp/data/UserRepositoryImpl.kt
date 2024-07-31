package com.example.emsgtestapp.data

import com.example.emsgtestapp.data.model.GitUser
import com.example.emsgtestapp.data.model.User
import com.example.emsgtestapp.data.remote.UsersApi
import com.example.emsgtestapp.domain.UserRepository
import com.example.emsgtestapp.domain.error.ApiError
import com.example.emsgtestapp.domain.error.NetworkError
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

    override suspend fun findUser(login: String): List<User> {
        try {
            val response = api.findUser(login)
            if (!response.isSuccessful) {
                throw ApiError(response.code(), response.message())
            }
            return response.body()?.items?: throw ApiError(response.code(), response.message())
        } catch (e: IOException) {
            throw NetworkError
        } catch (e: Exception) {
            throw UnknownError()
        }
    }

    override suspend fun getUserByLogin(login: String): GitUser {
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
}