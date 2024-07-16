package com.example.emsgtestapp.api

import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface UsersApi {
    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    @GET("users/{login}")
    suspend fun getUserById(@Path("login") login: String): Response<GitUser>

    @POST("users/{user}")
    suspend fun addUser(@Body user: User): Response<User>

    @DELETE("users/{user}")
    suspend fun deleteUser(@Path("name") name: String)

}



