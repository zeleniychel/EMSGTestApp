package com.example.emsgtestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User
import com.example.emsgtestapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel@Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    private val _data = MutableLiveData<List<GitUser>>(null)
    val data: LiveData<List<User>> = _data.map { gitUsers ->
        gitUsers?.map { gitUser ->
            User(
                id = gitUser.id,
                login = gitUser.login
            )
        } ?: emptyList()
    }
    init {
        getAll()
    }

    private fun getAll() = viewModelScope.launch {
        _data.value = repository.getAll()

    }

    fun addUser() = viewModelScope.launch {

    }

    fun deleteUser() = viewModelScope.launch {

    }

    fun findUser() = viewModelScope.launch {

    }
}