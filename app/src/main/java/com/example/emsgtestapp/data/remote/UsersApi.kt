package com.example.emsgtestapp.data.remote

import com.example.emsgtestapp.data.model.UserResponse
import com.example.emsgtestapp.data.model.GitUser
import com.example.emsgtestapp.data.model.User
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{login}")
    suspend fun getUserById(@Path("login") login: String): Response<GitUser>

    @GET("/search/users")
    suspend fun findUser(@Query("q")login: String): Response<UserResponse>
}



