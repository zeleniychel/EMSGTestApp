package com.example.emsgtestapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emsgtestapp.data.model.GitUser
import com.example.emsgtestapp.data.model.User
import com.example.emsgtestapp.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val repository: UserRepository,
) : ViewModel() {

    private val _data = MutableLiveData<List<User>>(emptyList())
    val data: LiveData<List<User>> = _data

    init {
        getUsers()
    }

    private fun getUsers() = viewModelScope.launch {
        _data.value = repository.getUsers()
    }

    fun findUser(login: String) {
        viewModelScope.launch {
            val users = if (login.isEmpty()) {
                repository.getUsers()
            } else {
                repository.findUser(login)
            }
            _data.value = users
        }
    }

    suspend fun getUserByLogin(login: String): GitUser {
        return repository.getUserByLogin(login)
    }
}