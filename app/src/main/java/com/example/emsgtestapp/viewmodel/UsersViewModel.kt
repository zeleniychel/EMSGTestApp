package com.example.emsgtestapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emsgtestapp.model.GitUser
import com.example.emsgtestapp.model.User
import com.example.emsgtestapp.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    private val _data = MutableLiveData<List<User>>(null)
    val data: LiveData<List<User>> = _data

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        _data.value = repository.getUsers()
    }

    suspend fun findUser(login: String):List<User>{
        return repository.findUser(login)
    }

    suspend fun getUserByLogin(login: String): GitUser {
        return repository.getUserByLogin(login)
    }
}